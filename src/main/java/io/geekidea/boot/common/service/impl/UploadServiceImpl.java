package io.geekidea.boot.common.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.geekidea.boot.auth.util.CommonLoginUtil;
import io.geekidea.boot.common.constant.UploadConstant;
import io.geekidea.boot.common.enums.FileServerType;
import io.geekidea.boot.common.enums.SysFileType;
import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.common.enums.UploadType;
import io.geekidea.boot.common.service.UploadService;
import io.geekidea.boot.common.vo.UploadVo;
import io.geekidea.boot.config.properties.FileProperties;
import io.geekidea.boot.config.properties.LocalFileProperties;
import io.geekidea.boot.framework.exception.UploadException;
import io.geekidea.boot.system.entity.SysFile;
import io.geekidea.boot.system.service.SysFileService;
import io.geekidea.boot.util.IpRegionUtil;
import io.geekidea.boot.util.IpUtil;
import io.geekidea.boot.util.SystemTypeUtil;
import io.geekidea.boot.util.TraceIdUtil;
import io.geekidea.boot.util.api.OssApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author geekidea
 * @date 2023/11/26
 **/
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private LocalFileProperties localFileProperties;

    @Autowired
    private SysFileService sysFileService;

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private final static String host = "http://v0.api.upyun.com";
    private final static String operator = "kuku";
    private final static String secret = "g9mDLlRgzQG7nsbsuPtlqHdi56ei9WkK";
    private final static String method = "PUT";
    private final static String uri = "/halo-blog-oss/kuku/";
    private final String responseURI =  "https://oss.snailuu.cn/kuku/";
    private static File file = new File("C:\\Users\\snailuu\\Downloads\\kuku-backend\\upload\\202406\\20240615174250558318259552261.jpg"); //本地文件路径
    private static String date = getRfc1123Time();


    /**
     * 获取签名
     * @return 签名
     */
    private static String getAuth(String fileUri) throws Exception {
        return sign(operator, md5(secret), method, fileUri, date, "", "");
    }

    private static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is unsupported", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest不支持MD5Util", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    private static byte[] hashHmac(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return mac.doFinal(data.getBytes());
    }
    public static String sign(String key, String secret, String method, String uri, String date, String policy,
                              String md5) throws Exception {
        String value = method + "&" + uri + "&" + date;
        if (policy != "") {
            value = value + "&" + policy;
        }
        if (md5 != "") {
            value = value + "&" + md5;
        }
        byte[] hmac = hashHmac(value, secret);
        System.out.println(value);
        String sign = Base64.getEncoder().encodeToString(hmac);
        return "UPYUN " + key + ":" + sign;
    }
    public static String getRfc1123Time() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
    @Override
    public UploadVo upload(String type, MultipartFile multipartFile) throws Exception {
        log.info("文件上传开始");
        if (StringUtils.isBlank(type)) {
            type = UploadType.ANY.getType();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("originalFilename:" + originalFilename);
        String contentType = multipartFile.getContentType();
        log.info("contentType:" + contentType);
        long size = multipartFile.getSize();
        log.info("size:" + size);
        double mb = size * 1.00 / 1024 / 1024;
        BigDecimal sizeMb = NumberUtil.round(mb, 2);
        log.info("sizeMb:" + sizeMb);
        // 获取文件后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        // 校验业务类型
        checkUploadType(type, sizeMb, extension);
        // 上传目录名称：年月
        String uploadDirName = DateUtil.format(new Date(), DatePattern.SIMPLE_MONTH_PATTERN);
        // 文件名称：年月日时分秒+15位唯一ID
        String formatDate = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
        String newFileName = formatDate + IdWorker.getId() + "." + extension;
        log.info("newFileName:" + newFileName);
        String url;
        File uploadFile = null;
        FileServerType fileServerType = fileProperties.getFileServerType();

        if (FileServerType.OSS == fileServerType) {
            // 阿里云OSS文件上传
            InputStream inputStream = multipartFile.getInputStream();
            url = OssApi.upload(inputStream, uploadDirName, newFileName);
        } else {

            String requestUri = uri+uploadDirName+"/"+newFileName;
            URL fileUrl = new URL(host+uri+uploadDirName+"/"+newFileName);
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            date=getRfc1123Time();
            conn.setRequestProperty("date",date);
            conn.setRequestProperty("Authorization",getAuth(requestUri));

            conn.connect();
            InputStream inputStream = new FileInputStream(file);
            OutputStream os =conn.getOutputStream();
            byte[] data = new byte[4096];
            int temp = 0;

            // 上传文件内容
            while ((temp = inputStream.read(data)) != -1) {
                os.write(data, 0, temp);
            }

            int responseCode = conn.getResponseCode(); //返回的状态码

            if (os != null) {
                os.close();
                os = null;
            }
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
            System.out.println(responseCode);


            // 上传文件到oss目录
            log.info("uploadFile:" + requestUri+"/"+uploadDirName);
            // 返回本地文件访问路径
            url = responseURI+"/"+uploadDirName+"/"+newFileName;
            log.info("url:" + url);
        }
        // 保存文件记录
        SysFile sysFile = new SysFile();
        sysFile.setTraceId(TraceIdUtil.getTraceId());
        sysFile.setServerType(fileServerType.getCode());
        sysFile.setUploadType(type);
        sysFile.setDirName(uploadDirName);
        sysFile.setOriginalFileName(originalFilename);
        sysFile.setFileName(newFileName);
        sysFile.setContentType(contentType);
        sysFile.setExtension(extension);
        sysFile.setSize(size);
        sysFile.setSizeMb(sizeMb);
        sysFile.setUrl(url);
        // 系统类型
        SystemType systemType = SystemTypeUtil.getSystemTypeByToken();
        if (systemType != null) {
            sysFile.setSystemType(systemType.getCode());
            sysFile.setUserId(CommonLoginUtil.getUserId(systemType));
        }
        // 文件物理路径，本地文件服务时才有
        if (uploadFile != null) {
            sysFile.setFilePath(uploadFile.getPath());
        }
        // 文件类型
        Integer fileType = getFileTypeByContentType(contentType);
        sysFile.setFileType(fileType);
        // 请求IP和区域信息
        String requestIp = IpUtil.getRequestIp();
        String ipAreaDesc = IpRegionUtil.getIpAreaDesc(requestIp);
        sysFile.setIp(requestIp);
        sysFile.setIpArea(ipAreaDesc);
        // 保存文件记录
        sysFileService.save(sysFile);
        UploadVo uploadVo = new UploadVo();
        uploadVo.setId(sysFile.getId());
        uploadVo.setOriginalFilename(originalFilename);
        uploadVo.setFilename(newFileName);
        uploadVo.setUrl(url);
        uploadVo.setSize(size);
        uploadVo.setSizeMb(sizeMb);
        log.info("文件上传结束");
        return uploadVo;
    }

    private Integer getFileTypeByContentType(String contentType) {
        if (StringUtils.isBlank(contentType)) {
            return SysFileType.FILE.getCode();
        }
        if (contentType.startsWith(UploadConstant.IMAGE_TYPE)) {
            return SysFileType.IMAGE.getCode();
        } else if (contentType.startsWith(UploadConstant.AUDIO_TYPE) || contentType.startsWith(UploadConstant.VIDEO_TYPE)) {
            return SysFileType.VIDEO.getCode();
        } else if (contentType.startsWith(UploadConstant.XLS_TYPE) ||
                contentType.startsWith(UploadConstant.XLSX_TYPE) ||
                contentType.startsWith(UploadConstant.DOC_TYPE) ||
                contentType.startsWith(UploadConstant.PPT_TYPE) ||
                contentType.startsWith(UploadConstant.PDF_TYPE) ||
                contentType.startsWith(UploadConstant.OFFICE_TYPE)
        ) {
            return SysFileType.OFFICE.getCode();
        } else {
            return SysFileType.FILE.getCode();
        }
    }

    @Override
    public List<UploadVo> uploadBatch(String type, List<MultipartFile> multipartFiles) throws Exception {
        log.info("批量文件上传结束");
        List<UploadVo> uploadVos = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            UploadVo upload = upload(type, multipartFile);
            uploadVos.add(upload);
        }
        log.info("批量文件上传结束");
        return uploadVos;
    }

    private void checkUploadType(String type, BigDecimal sizeMb, String extension) throws Exception {
        UploadType uploadType = UploadType.get(type);
        System.out.println("uploadType = " + uploadType);
        if (UploadType.ANY == uploadType) {
            return;
        }
        Integer maxSizeMb = uploadType.getMaxSizeMb();
        List<String> extensions = uploadType.getExtensions();
        if (sizeMb.doubleValue() > maxSizeMb) {
            throw new UploadException("超过文件大小限制，请上传" + maxSizeMb + "MB以内的文件");
        }
        extension = extension.toLowerCase();
        if (!extensions.contains(extension)) {
            throw new UploadException("文件格式错误，请上传" + extensions.toString() + "格式的文件");
        }
    }

}
