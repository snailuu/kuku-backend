package io.snailuu.boot.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.framework.page.OrderByItem;
import io.snailuu.boot.framework.page.OrderMapping;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.test.dto.TypeTicketDto;
import io.snailuu.boot.test.entity.TypeTicket;
import io.snailuu.boot.test.mapper.TypeTicketMapper;
import io.snailuu.boot.test.query.TypeTicketQuery;
import io.snailuu.boot.test.service.TypeTicketService;
import io.snailuu.boot.test.vo.TypeTicketVo;
import io.snailuu.boot.test.query.AppTypeTicketQuery;
import io.snailuu.boot.test.vo.AppTypeTicketVo;
import io.snailuu.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 工单类型表 服务实现类
 *
 * @author snailuu
 * @since 2024-06-17
 */
@Slf4j
@Service
public class TypeTicketServiceImpl extends ServiceImpl<TypeTicketMapper, TypeTicket> implements TypeTicketService {

    @Autowired
    private TypeTicketMapper typeTicketMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addTypeTicket(TypeTicketDto dto) {
        TypeTicket typeTicket = new TypeTicket();
        BeanUtils.copyProperties(dto, typeTicket);
        return save(typeTicket);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTypeTicket(TypeTicketDto dto) {
        Integer id = dto.getId();
        TypeTicket typeTicket = getById(id);
        if (typeTicket == null) {
            throw new BusinessException("工单类型表不存在");
        }
        BeanUtils.copyProperties(dto, typeTicket);
        return updateById(typeTicket);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTypeTicket(Integer id) {
        return removeById(id);
    }

    @Override
    public TypeTicketVo getTypeTicketById(Integer id) {
        return typeTicketMapper.getTypeTicketById(id);
    }

    @Override
    public Paging<TypeTicketVo> getTypeTicketPage(TypeTicketQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<TypeTicketVo> list = typeTicketMapper.getTypeTicketPage(query);
        Paging<TypeTicketVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public AppTypeTicketVo getAppTypeTicketById(Integer id) {
        return typeTicketMapper.getAppTypeTicketById(id);
    }

    @Override
    public Paging<AppTypeTicketVo> getAppTypeTicketPage(AppTypeTicketQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<AppTypeTicketVo> list = typeTicketMapper.getAppTypeTicketPage(query);
        Paging<AppTypeTicketVo> paging = new Paging<>(list);
        return paging;
    }

}
