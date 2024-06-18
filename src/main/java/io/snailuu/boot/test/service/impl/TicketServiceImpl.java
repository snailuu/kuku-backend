package io.snailuu.boot.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.framework.page.OrderByItem;
import io.snailuu.boot.framework.page.OrderMapping;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.test.dto.TicketDto;
import io.snailuu.boot.test.entity.Ticket;
import io.snailuu.boot.test.mapper.TicketMapper;
import io.snailuu.boot.test.query.TicketQuery;
import io.snailuu.boot.test.service.TicketService;
import io.snailuu.boot.test.vo.TicketVo;
import io.snailuu.boot.test.query.AppTicketQuery;
import io.snailuu.boot.test.vo.AppTicketVo;
import io.snailuu.boot.util.PagingUtil;
import io.snailuu.boot.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 工单 服务实现类
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addTicket(TicketDto dto) {
        Ticket ticket = new Ticket();
        dto.setUuid(UUIDUtil.getUuid().substring(0,10));
        BeanUtils.copyProperties(dto, ticket);
        return save(ticket);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTicket(TicketDto dto) {
        Long id = dto.getId();
        Ticket ticket = getById(id);
        if (ticket == null) {
            throw new BusinessException("工单不存在");
        }
        BeanUtils.copyProperties(dto, ticket);
        return updateById(ticket);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTicket(Long id) {
        return removeById(id);
    }

    @Override
    public TicketVo getTicketById(Long id) {
        return ticketMapper.getTicketById(id);
    }

    @Override
    public Paging<TicketVo> getTicketPage(TicketQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<TicketVo> list = ticketMapper.getTicketPage(query);
        Paging<TicketVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public AppTicketVo getAppTicketById(Long id) {
        return ticketMapper.getAppTicketById(id);
    }

    @Override
    public Paging<AppTicketVo> getAppTicketPage(AppTicketQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<AppTicketVo> list = ticketMapper.getAppTicketPage(query);
        Paging<AppTicketVo> paging = new Paging<>(list);
        return paging;
    }

}
