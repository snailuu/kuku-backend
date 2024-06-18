package io.snailuu.boot.framework.bean;

import lombok.Data;

/**
 * @author snailuu
 * @date 2023/6/23
 **/
@Data
public class IpRegion {

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域描述
     */
    private String areaDesc;

    /**
     * 运营商
     */
    private String isp;

}
