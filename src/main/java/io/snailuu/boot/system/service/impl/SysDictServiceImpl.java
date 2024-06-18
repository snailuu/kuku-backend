package io.snailuu.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.framework.page.OrderByItem;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.system.dto.SysDictDto;
import io.snailuu.boot.system.entity.SysDict;
import io.snailuu.boot.system.mapper.SysDictMapper;
import io.snailuu.boot.system.query.SysDictAppQuery;
import io.snailuu.boot.system.query.SysDictQuery;
import io.snailuu.boot.system.service.SysDictService;
import io.snailuu.boot.system.vo.AppSysDictVo;
import io.snailuu.boot.system.vo.SysDictVo;
import io.snailuu.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典数据 服务实现类
 *
 * @author snailuu
 * @since 2023-11-25
 */
@Slf4j
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysDict(SysDictDto dto) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(dto, sysDict);
        return save(sysDict);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysDict(SysDictDto dto) {
        Long id = dto.getId();
        SysDict sysDict = getById(id);
        if (sysDict == null) {
            throw new BusinessException("字典数据不存在");
        }
        BeanUtils.copyProperties(dto, sysDict);
        return updateById(sysDict);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysDict(Long id) {
        return removeById(id);
    }

    @Override
    public SysDictVo getSysDictById(Long id) {
        return sysDictMapper.getSysDictById(id);
    }

    @Override
    public Paging<SysDictVo> getSysDictPage(SysDictQuery query) {
        PagingUtil.handlePage(query, OrderByItem.orderBy("status desc,sort,id"));
        List<SysDictVo> list = sysDictMapper.getSysDictPage(query);
        Paging<SysDictVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public Map<String, List<AppSysDictVo>> getAppSysDictList(SysDictAppQuery query) {
        List<AppSysDictVo> list = sysDictMapper.getAppSysDictList(query);
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, List<AppSysDictVo>> map = list.stream().collect(Collectors.groupingBy(AppSysDictVo::getDictCode));
            return map;
        }
        return null;
    }

    @Override
    public List<SysDict> getSysDictList(String dictCode) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDict::getDictCode, dictCode);
        wrapper.eq(SysDict::getStatus, true);
        wrapper.orderByAsc(SysDict::getSort);
        return list(wrapper);
    }

    @Override
    public SysDict getSysDictByValue(String dictCode, Serializable value) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDict::getDictCode, dictCode);
        wrapper.eq(SysDict::getValue, value);
        wrapper.eq(SysDict::getStatus, true);
        return getOne(wrapper);
    }

    @Override
    public String getSysDictLabelByValue(String dictCode, Serializable value) {
        SysDict sysDict = getSysDictByValue(dictCode, value);
        if (sysDict != null) {
            return sysDict.getLabel();
        }
        return null;
    }

}
