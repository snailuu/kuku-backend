package io.snailuu.boot.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.framework.page.OrderByItem;
import io.snailuu.boot.framework.page.OrderMapping;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.system.entity.SysLog;
import io.snailuu.boot.system.mapper.SysLogMapper;
import io.snailuu.boot.system.query.SysLogQuery;
import io.snailuu.boot.system.service.SysLogService;
import io.snailuu.boot.system.vo.SysLogVo;
import io.snailuu.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统日志 服务实现类
 *
 * @author snailuu
 * @since 2023-02-16
 */
@Slf4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLogVo getSysLogById(Long id) {
        return sysLogMapper.getSysLogById(id);
    }

    @Override
    public Paging<SysLogVo> getSysLogPage(SysLogQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        orderMapping.put("createTime", "create_time");
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<SysLogVo> list = sysLogMapper.getSysLogPage(query);
        Paging<SysLogVo> paging = new Paging<>(list);
        return paging;
    }

}
