package io.geekidea.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.test.entity.TypeTicket;
import io.geekidea.boot.test.query.TypeTicketQuery;
import io.geekidea.boot.test.vo.TypeTicketVo;
import io.geekidea.boot.test.query.AppTypeTicketQuery;
import io.geekidea.boot.test.vo.AppTypeTicketVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工单类型表 Mapper 接口
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Mapper
public interface TypeTicketMapper extends BaseMapper<TypeTicket> {

    /**
     * 工单类型表详情
     *
     * @param id
     * @return
     */
    TypeTicketVo getTypeTicketById(Integer id);

    /**
     * 工单类型表分页列表
     *
     * @param query
     * @return
     */
    List<TypeTicketVo> getTypeTicketPage(TypeTicketQuery query);

    /**
     * App工单类型表详情
     *
     * @param id
     * @return
     */
    AppTypeTicketVo getAppTypeTicketById(Integer id);

    /**
     * App工单类型表分页列表
     *
     * @param query
     * @return
     */
    List<AppTypeTicketVo> getAppTypeTicketPage(AppTypeTicketQuery query);

}
