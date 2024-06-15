package io.geekidea.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.test.entity.WorkingSchedule;
import io.geekidea.boot.test.query.WorkingScheduleQuery;
import io.geekidea.boot.test.vo.WorkingScheduleVo;
import io.geekidea.boot.test.query.AppWorkingScheduleQuery;
import io.geekidea.boot.test.vo.AppWorkingScheduleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人员排班 Mapper 接口
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Mapper
public interface WorkingScheduleMapper extends BaseMapper<WorkingSchedule> {

    /**
     * 人员排班详情
     *
     * @param id
     * @return
     */
    WorkingScheduleVo getWorkingScheduleById(Long id);

    /**
     * 人员排班分页列表
     *
     * @param query
     * @return
     */
    List<WorkingScheduleVo> getWorkingSchedulePage(WorkingScheduleQuery query);

    /**
     * App人员排班详情
     *
     * @param id
     * @return
     */
    AppWorkingScheduleVo getAppWorkingScheduleById(Long id);

    /**
     * App人员排班分页列表
     *
     * @param query
     * @return
     */
    List<AppWorkingScheduleVo> getAppWorkingSchedulePage(AppWorkingScheduleQuery query);

    /**
     * 查看某一天的值班信息
     */
    List<AppWorkingScheduleVo> getWorkingScheduleByDate(String workingDate);
}
