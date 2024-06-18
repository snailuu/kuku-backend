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
@ConfigurationProperties(prefix = "login")
public class LoginProperties {

    /**
     * 排除的路径
     */
    private List<String> excludePaths;

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }


}
