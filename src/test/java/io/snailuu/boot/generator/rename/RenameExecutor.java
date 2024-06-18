package io.snailuu.boot.generator.rename;

import io.snailuu.boot.generator.handler.RenameHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author snailuu
 * @date 2023/11/24
 **/
@Slf4j
public class RenameExecutor {

    public static void main(String[] args) throws Exception {
        // 当前项目包名称
        String currentPackageName = "io.snailuu.boot";
        // 目标项目包名称
        String targetPackageName = "io.snailuu.boot";
        // artifactId名称
        String targetArtifactId = "spring-boot-plus";
        RenameHandler.execute(currentPackageName, targetPackageName, targetArtifactId);
    }

}
