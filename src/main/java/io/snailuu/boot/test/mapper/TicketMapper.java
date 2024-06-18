package io.snailuu.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.snailuu.boot.test.entity.Ticket;
import io.snailuu.boot.test.query.TicketQuery;
import io.snailuu.boot.test.vo.TicketVo;
import io.snailuu.boot.test.query.AppTicketQuery;
import io.snailuu.boot.test.vo.AppTicketVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工单 Mapper 接口
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Mapper
public interface TicketMapper extends BaseMapper<Ticket> {

    /**
     * 工单详情
     *
     * @param id
     * @return
     */
    TicketVo getTicketById(Long id);

    /**
     * 工单分页列表
     *
     * @param query
     * @return
     */
    List<TicketVo> getTicketPage(TicketQuery query);

    /**
     * 审核工单
     */
    boolean updateTicketStatus (Long id, int status);

    /**
     * App工单详情
     *
     * @param id
     * @return
     */
    AppTicketVo getAppTicketById(Long id);

    /**
     * App工单分页列表
     *
     * @param query
     * @return
     */
    List<AppTicketVo> getAppTicketPage(AppTicketQuery query);

}
