package io.snailuu.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.system.entity.SysLog;
import io.snailuu.boot.system.query.SysLogQuery;
import io.snailuu.boot.system.vo.SysLogVo;

/**
 * 系统日志 服务接口
 *
 * @author snailuu
 * @since 2023-02-16
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 系统日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysLogVo getSysLogById(Long id);

    /**
     * 系统日志分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysLogVo> getSysLogPage(SysLogQuery query);

}
