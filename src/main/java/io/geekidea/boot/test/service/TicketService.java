package io.geekidea.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.test.dto.TicketDto;
import io.geekidea.boot.test.entity.Ticket;
import io.geekidea.boot.test.query.TicketQuery;
import io.geekidea.boot.test.vo.TicketVo;
import io.geekidea.boot.test.query.AppTicketQuery;
import io.geekidea.boot.test.vo.AppTicketVo;


/**
 * 工单 服务接口
 *
 * @author geekidea
 * @since 2024-06-15
 */
public interface TicketService extends IService<Ticket> {

    /**
     * 添加工单
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addTicket(TicketDto dto);

    /**
     * 修改工单
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateTicket(TicketDto dto);

    /**
     * 删除工单
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteTicket(Long id);

    /**
     * 工单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    TicketVo getTicketById(Long id);

    /**
     * 工单分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<TicketVo> getTicketPage(TicketQuery query);

    /**
     * App工单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    AppTicketVo getAppTicketById(Long id);

    /**
     * App工单分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<AppTicketVo> getAppTicketPage(AppTicketQuery query);

}
