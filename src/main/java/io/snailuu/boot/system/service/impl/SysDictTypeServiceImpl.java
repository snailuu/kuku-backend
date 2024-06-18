package io.snailuu.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.snailuu.boot.framework.exception.BusinessException;
import io.snailuu.boot.system.dto.SysDictTypeDto;
import io.snailuu.boot.system.entity.SysDictType;
import io.snailuu.boot.system.mapper.SysDictTypeMapper;
import io.snailuu.boot.system.query.SysDictTypeQuery;
import io.snailuu.boot.system.service.SysDictTypeService;
import io.snailuu.boot.system.vo.SysDictTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典类型 服务实现类
 *
 * @author snailuu
 * @since 2023-11-25
 */
@Slf4j
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysDictType(SysDictTypeDto dto) {
        checkCodeExists(dto.getCode());
        SysDictType sysDictType = new SysDictType();
        BeanUtils.copyProperties(dto, sysDictType);
        return save(sysDictType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysDictType(SysDictTypeDto dto) {
        Long id = dto.getId();
        SysDictType sysDictType = getById(id);
        if (sysDictType == null) {
            throw new BusinessException("字典类型不存在");
        }
        sysDictType.setIsSystem(dto.getIsSystem());
        sysDictType.setName(dto.getName());
        sysDictType.setRemark(dto.getRemark());
        return updateById(sysDictType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysDictType(Long id) {
        SysDictType sysDictType = getById(id);
        if (sysDictType == null) {
            throw new BusinessException("系统字典类型不存在");
        }
        Boolean isSystem = sysDictType.getIsSystem();
        if (isSystem) {
            throw new BusinessException("系统类型不能删除");
        }
        return removeById(id);
    }

    @Override
    public SysDictTypeVo getSysDictTypeById(Long id) {
        return sysDictTypeMapper.getSysDictTypeById(id);
    }

    @Override
    public List<SysDictTypeVo> getSysDictTypeList(SysDictTypeQuery query) {
        List<SysDictTypeVo> list = sysDictTypeMapper.getSysDictTypeList(query);
        return list;
    }

    @Override
    public void checkCodeExists(String code) {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictType::getCode, code);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(code + "类型编码已经存在");
        }
    }

}
