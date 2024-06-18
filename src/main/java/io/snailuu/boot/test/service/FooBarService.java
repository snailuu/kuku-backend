package io.snailuu.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.test.dto.FooBarDto;
import io.snailuu.boot.test.entity.FooBar;
import io.snailuu.boot.test.query.FooBarQuery;
import io.snailuu.boot.test.vo.FooBarVo;
import io.snailuu.boot.test.query.AppFooBarQuery;
import io.snailuu.boot.test.vo.AppFooBarVo;


/**
 * FooBar 服务接口
 *
 * @author snailuu
 * @since 2024-06-15
 */
public interface FooBarService extends IService<FooBar> {

    /**
     * 添加FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addFooBar(FooBarDto dto);

    /**
     * 修改FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateFooBar(FooBarDto dto);

    /**
     * 删除FooBar
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteFooBar(Long id);

    /**
     * FooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    FooBarVo getFooBarById(Long id);

    /**
     * FooBar分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<FooBarVo> getFooBarPage(FooBarQuery query);

    /**
     * AppFooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    AppFooBarVo getAppFooBarById(Long id);

    /**
     * AppFooBar分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<AppFooBarVo> getAppFooBarPage(AppFooBarQuery query);

}
