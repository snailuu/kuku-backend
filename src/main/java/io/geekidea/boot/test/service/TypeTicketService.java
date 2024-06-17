package io.geekidea.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.test.dto.TypeTicketDto;
import io.geekidea.boot.test.entity.TypeTicket;
import io.geekidea.boot.test.query.TypeTicketQuery;
import io.geekidea.boot.test.vo.TypeTicketVo;
import io.geekidea.boot.test.query.AppTypeTicketQuery;
import io.geekidea.boot.test.vo.AppTypeTicketVo;


/**
 * 工单类型表 服务接口
 *
 * @author snailuu
 * @since 2024-06-17
 */
public interface TypeTicketService extends IService<TypeTicket> {

    /**
     * 添加工单类型表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addTypeTicket(TypeTicketDto dto);

    /**
     * 修改工单类型表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateTypeTicket(TypeTicketDto dto);

    /**
     * 删除工单类型表
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteTypeTicket(Integer id);

    /**
     * 工单类型表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    TypeTicketVo getTypeTicketById(Integer id);

    /**
     * 工单类型表分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<TypeTicketVo> getTypeTicketPage(TypeTicketQuery query);

    /**
     * App工单类型表详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    AppTypeTicketVo getAppTypeTicketById(Integer id);

    /**
     * App工单类型表分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<AppTypeTicketVo> getAppTypeTicketPage(AppTypeTicketQuery query);

}
