package io.geekidea.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.test.entity.Ticket;
import io.geekidea.boot.test.query.TicketQuery;
import io.geekidea.boot.test.vo.TicketVo;
import io.geekidea.boot.test.query.AppTicketQuery;
import io.geekidea.boot.test.vo.AppTicketVo;
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
