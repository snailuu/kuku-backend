package io.geekidea.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.test.entity.Arrange;
import io.geekidea.boot.test.query.ArrangeQuery;
import io.geekidea.boot.test.vo.ArrangeVo;
import io.geekidea.boot.test.query.AppArrangeQuery;
import io.geekidea.boot.test.vo.AppArrangeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 值班安排 Mapper 接口
 *
 * @author snailuu
 * @since 2024-06-15
 */
@Mapper
public interface ArrangeMapper extends BaseMapper<Arrange> {

    /**
     * 值班安排详情
     *
     * @param id
     * @return
     */
    ArrangeVo getArrangeById(Long id);

    /**
     * 值班安排分页列表
     *
     * @param query
     * @return
     */
    List<ArrangeVo> getArrangePage(ArrangeQuery query);

    /**
     * App值班安排详情
     *
     * @param id
     * @return
     */
    AppArrangeVo getAppArrangeById(Long id);

    /**
     * App值班安排分页列表
     *
     * @param query
     * @return
     */
    List<AppArrangeVo> getAppArrangePage(AppArrangeQuery query);

}
