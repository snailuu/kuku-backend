package io.geekidea.boot.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.OrderMapping;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.test.dto.WorkingScheduleDto;
import io.geekidea.boot.test.entity.WorkingSchedule;
import io.geekidea.boot.test.mapper.WorkingScheduleMapper;
import io.geekidea.boot.test.query.WorkingScheduleQuery;
import io.geekidea.boot.test.service.WorkingScheduleService;
import io.geekidea.boot.test.vo.WorkingScheduleVo;
import io.geekidea.boot.test.query.AppWorkingScheduleQuery;
import io.geekidea.boot.test.vo.AppWorkingScheduleVo;
import io.geekidea.boot.user.service.UserService;
import io.geekidea.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 人员排班 服务实现类
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Slf4j
@Service
public class WorkingScheduleServiceImpl extends ServiceImpl<WorkingScheduleMapper, WorkingSchedule> implements WorkingScheduleService {

    @Autowired
    private WorkingScheduleMapper workingScheduleMapper;

    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addWorkingSchedule(WorkingScheduleDto dto) {
        WorkingSchedule workingSchedule = new WorkingSchedule();
        BeanUtils.copyProperties(dto, workingSchedule);
        return save(workingSchedule);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateWorkingSchedule(WorkingScheduleDto dto) {
        Long id = dto.getId();
        WorkingSchedule workingSchedule = getById(id);
        if (workingSchedule == null) {
            throw new BusinessException("人员排班不存在");
        }
        BeanUtils.copyProperties(dto, workingSchedule);
        return updateById(workingSchedule);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteWorkingSchedule(Long id) {
        return removeById(id);
    }

    @Override
    public WorkingScheduleVo getWorkingScheduleById(Long id) {
        return workingScheduleMapper.getWorkingScheduleById(id);
    }

    @Override
    public Paging<WorkingScheduleVo> getWorkingSchedulePage(WorkingScheduleQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<WorkingScheduleVo> list = workingScheduleMapper.getWorkingSchedulePage(query);
        for(WorkingScheduleVo workingScheduleVo : list){
            workingScheduleVo.setNickname(userService.getUserById(workingScheduleVo.getUserId()).getNickname());
        }
        Paging<WorkingScheduleVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public AppWorkingScheduleVo getAppWorkingScheduleById(Long id) {
        return workingScheduleMapper.getAppWorkingScheduleById(id);
    }

    @Override
    public Paging<AppWorkingScheduleVo> getAppWorkingSchedulePage(AppWorkingScheduleQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<AppWorkingScheduleVo> list = workingScheduleMapper.getAppWorkingSchedulePage(query);
        Paging<AppWorkingScheduleVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public Paging<AppWorkingScheduleVo> getWorkingScheduleByDate(String workingDate) {
        return null;
    }

}
