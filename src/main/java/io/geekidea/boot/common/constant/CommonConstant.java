package io.geekidea.boot.common.constant;

/**
 * 公共常量
 *
 * @author geekidea
 * @date 2018-11-08
 */
public interface CommonConstant {

    /**
     * 项目包名称
     */
    String PACKAGE_NAME = "io.geekidea.boot";

    /**
     * 公共包名称
     */
    String COMMON_PACKAGE_NAME = PACKAGE_NAME + ".common";

    /**
     * 默认页码为1
     */
    Integer DEFAULT_PAGE_INDEX = 1;

    /**
     * 默认页大小为10
     */
    Integer DEFAULT_PAGE_SIZE = 10;

    String COMMA = ",";

    /**
     * 请求的原始字符串
     */
    String REQUEST_PARAM_BODY_STRING = "REQUEST_PARAM_BODY_STRING";

    /**
     * 请求的token
     */
    String REQUEST_PARAM_EXCLUDE_PATH = "REQUEST_PARAM_EXCLUDE_PATH";

    /**
     * 日志链路ID
     */
    String TRACE_ID = "traceId";

    /**
     * 请求IP
     */
    String IP = "ip";

    /**
     * 1000
     */
    int ONE_THOUSAND = 1000;

    /**
     * Knife4j
     */
    String KNIFE4J = "Knife4j";

    /**
     * swaggerUI访问路径
     */
    String SWAGGER_UI_PATH = "/swagger-ui/index.html";

    /**
     * xls后缀
     */
    String XLS = "xls";

    /**
     * xlsx后缀
     */
    String XLSX = "xlsx";

    /**
     * 当前项目目录
     */
    String USER_DIR = System.getProperty("user.dir");

    /**
     * 文件上传目录
     */
    String UPLOAD = "upload";

    /**
     * 正斜杠
     */
    String SLASH = "/";

    /**
     * 反斜杠
     */
    String BACKSLASH = "\\";

    /**
     * 双反斜杠
     */
    String DOUBLE_BACKSLASH = "\\\\";

}
