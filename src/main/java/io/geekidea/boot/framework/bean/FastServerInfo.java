package io.geekidea.boot.framework.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Schema(description = "服务信息(用于首页数据)")
public class FastServerInfo implements Serializable {
    private static final long serialVersionUID = 3621691986817747918L;

    @Schema(description = "CPU信息")
    private Cpu cpu;

    @Schema(description = "内存信息")
    private double memory;

    @Schema(description = "JVM信息")
    private double jvm;

    @Schema(description = "磁盘信息")
    private double disk;
}
