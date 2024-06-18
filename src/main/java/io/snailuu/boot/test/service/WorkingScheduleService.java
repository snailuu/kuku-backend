package io.snailuu.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.test.dto.WorkingScheduleDto;
import io.snailuu.boot.test.entity.WorkingSchedule;
import io.snailuu.boot.test.query.WorkingScheduleQuery;
import io.snailuu.boot.test.vo.WorkingScheduleVo;
import io.snailuu.boot.test.query.AppWorkingScheduleQuery;
import io.snailuu.boot.test.vo.AppWorkingScheduleVo;


/**
 * 人员排班 服务接口
 *
 * @author snailuu
 * @since 2024-06-15
 */
public interface WorkingScheduleService extends IService<WorkingSchedule> {

    /**
     * 添加人员排班
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addWorkingSchedule(WorkingScheduleDto dto);

    /**
     * 修改人员排班
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateWorkingSchedule(WorkingScheduleDto dto);

    /**
     * 删除人员排班
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteWorkingSchedule(Long id);

    /**
     * 人员排班详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    WorkingScheduleVo getWorkingScheduleById(Long id);

    /**
     * 人员排班分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<WorkingScheduleVo> getWorkingSchedulePage(WorkingScheduleQuery query);

    /**
     * App人员排班详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    AppWorkingScheduleVo getAppWorkingScheduleById(Long id);

    /**
     * App人员排班分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<AppWorkingScheduleVo> getAppWorkingSchedulePage(AppWorkingScheduleQuery query);

    /**
     * 根据日期获取排班信息
     *
     * @param workingDate
     * @return
     */
    Paging<AppWorkingScheduleVo> getWorkingScheduleByDate(String workingDate);


}
