package io.snailuu.boot.config.properties;

import io.snailuu.boot.common.enums.FileServerType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 上传文件属性配置
 *
 * @author snailuu
 * @date 2023/06/18
 **/
@Data
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /**
     * 文件服务类型
     */
    private FileServerType fileServerType;

}
