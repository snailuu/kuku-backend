package io.snailuu.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.snailuu.boot.test.vo.DataInfoVo;

public interface DataInfoService extends IService<DataInfoVo> {
    DataInfoVo getDataInfo();
}
