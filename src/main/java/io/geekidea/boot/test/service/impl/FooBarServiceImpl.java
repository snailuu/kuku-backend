package io.geekidea.boot.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.OrderMapping;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.test.dto.FooBarDto;
import io.geekidea.boot.test.entity.FooBar;
import io.geekidea.boot.test.mapper.FooBarMapper;
import io.geekidea.boot.test.query.FooBarQuery;
import io.geekidea.boot.test.service.FooBarService;
import io.geekidea.boot.test.vo.FooBarVo;
import io.geekidea.boot.test.query.AppFooBarQuery;
import io.geekidea.boot.test.vo.AppFooBarVo;
import io.geekidea.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * FooBar 服务实现类
 *
 * @author geekidea
 * @since 2024-06-15
 */
@Slf4j
@Service
public class FooBarServiceImpl extends ServiceImpl<FooBarMapper, FooBar> implements FooBarService {

    @Autowired
    private FooBarMapper fooBarMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addFooBar(FooBarDto dto) {
        FooBar fooBar = new FooBar();
        BeanUtils.copyProperties(dto, fooBar);
        return save(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateFooBar(FooBarDto dto) {
        Long id = dto.getId();
        FooBar fooBar = getById(id);
        if (fooBar == null) {
            throw new BusinessException("FooBar不存在");
        }
        BeanUtils.copyProperties(dto, fooBar);
        return updateById(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFooBar(Long id) {
        return removeById(id);
    }

    @Override
    public FooBarVo getFooBarById(Long id) {
        return fooBarMapper.getFooBarById(id);
    }

    @Override
    public Paging<FooBarVo> getFooBarPage(FooBarQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        orderMapping.put("createTime", "create_time");
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<FooBarVo> list = fooBarMapper.getFooBarPage(query);
        Paging<FooBarVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public AppFooBarVo getAppFooBarById(Long id) {
        return fooBarMapper.getAppFooBarById(id);
    }

    @Override
    public Paging<AppFooBarVo> getAppFooBarPage(AppFooBarQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        orderMapping.put("createTime", "create_time");
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<AppFooBarVo> list = fooBarMapper.getAppFooBarPage(query);
        Paging<AppFooBarVo> paging = new Paging<>(list);
        return paging;
    }

}
