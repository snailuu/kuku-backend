package io.snailuu.boot.config.properties;

import io.snailuu.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author snailuu
 * @date 2022/6/26
 **/
@Data
@Component
@ConfigurationProperties(prefix = "login.common")
public class LoginCommonProperties {

    /**
     * 是否启用拦截器
     */
    private boolean enable;

    /**
     * 包含的路径
     */
    private List<String> includePaths;

    public void setExcludePaths(List<String> includePaths) {
        this.includePaths = YamlUtil.parseListArray(includePaths);
    }

}
