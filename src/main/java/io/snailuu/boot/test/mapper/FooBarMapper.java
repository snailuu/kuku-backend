package io.snailuu.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.snailuu.boot.test.entity.FooBar;
import io.snailuu.boot.test.query.FooBarQuery;
import io.snailuu.boot.test.vo.FooBarVo;
import io.snailuu.boot.test.query.AppFooBarQuery;
import io.snailuu.boot.test.vo.AppFooBarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FooBar Mapper 接口
 *
 * @author snailuu
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
