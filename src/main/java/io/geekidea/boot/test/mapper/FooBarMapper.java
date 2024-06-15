package io.geekidea.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.test.entity.FooBar;
import io.geekidea.boot.test.query.FooBarQuery;
import io.geekidea.boot.test.vo.FooBarVo;
import io.geekidea.boot.test.query.AppFooBarQuery;
import io.geekidea.boot.test.vo.AppFooBarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FooBar Mapper 接口
 *
 * @author geekidea
 * @since 2024-06-15
 */
@Mapper
public interface FooBarMapper extends BaseMapper<FooBar> {

    /**
     * FooBar详情
     *
     * @param id
     * @return
     */
    FooBarVo getFooBarById(Long id);

    /**
     * FooBar分页列表
     *
     * @param query
     * @return
     */
    List<FooBarVo> getFooBarPage(FooBarQuery query);

    /**
     * AppFooBar详情
     *
     * @param id
     * @return
     */
    AppFooBarVo getAppFooBarById(Long id);

    /**
     * AppFooBar分页列表
     *
     * @param query
     * @return
     */
    List<AppFooBarVo> getAppFooBarPage(AppFooBarQuery query);

}
