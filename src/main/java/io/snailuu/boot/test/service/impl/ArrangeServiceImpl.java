package io.snailuu.boot.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.framework.page.OrderByItem;
import io.snailuu.boot.framework.page.OrderMapping;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.test.dto.ArrangeDto;
import io.snailuu.boot.test.entity.Arrange;
import io.snailuu.boot.test.mapper.ArrangeMapper;
import io.snailuu.boot.test.query.ArrangeQuery;
import io.snailuu.boot.test.service.ArrangeService;
import io.snailuu.boot.test.vo.ArrangeVo;
import io.snailuu.boot.test.query.AppArrangeQuery;
import io.snailuu.boot.test.vo.AppArrangeVo;
import io.snailuu.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 值班安排 服务实现类
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@Service
public class ArrangeServiceImpl extends ServiceImpl<ArrangeMapper, Arrange> implements ArrangeService {

    @Autowired
    private ArrangeMapper arrangeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addArrange(ArrangeDto dto) {
        Arrange arrange = new Arrange();
        BeanUtils.copyProperties(dto, arrange);
        return save(arrange);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateArrange(ArrangeDto dto) {
        Long id = dto.getId();
        Arrange arrange = getById(id);
        if (arrange == null) {
            throw new BusinessException("值班安排不存在");
        }
        BeanUtils.copyProperties(dto, arrange);
        return updateById(arrange);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteArrange(Long id) {
        return removeById(id);
    }

    @Override
    public ArrangeVo getArrangeById(Long id) {
        return arrangeMapper.getArrangeById(id);
    }

    @Override
    public Paging<ArrangeVo> getArrangePage(ArrangeQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<ArrangeVo> list = arrangeMapper.getArrangePage(query);
        Paging<ArrangeVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public AppArrangeVo getAppArrangeById(Long id) {
        return arrangeMapper.getAppArrangeById(id);
    }

    @Override
    public Paging<AppArrangeVo> getAppArrangePage(AppArrangeQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<AppArrangeVo> list = arrangeMapper.getAppArrangePage(query);
        Paging<AppArrangeVo> paging = new Paging<>(list);
        return paging;
    }

}
