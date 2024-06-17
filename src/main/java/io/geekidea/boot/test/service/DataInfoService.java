package io.geekidea.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.test.vo.DataInfoVo;
import io.geekidea.boot.test.vo.TicketVo;

import java.util.List;

public interface DataInfoService extends IService<DataInfoVo> {
    DataInfoVo getDataInfo();
}
