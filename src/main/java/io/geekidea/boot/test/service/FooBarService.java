package io.geekidea.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.test.dto.FooBarDto;
import io.geekidea.boot.test.entity.FooBar;
import io.geekidea.boot.test.query.FooBarQuery;
import io.geekidea.boot.test.vo.FooBarVo;
import io.geekidea.boot.test.query.AppFooBarQuery;
import io.geekidea.boot.test.vo.AppFooBarVo;


/**
 * FooBar 服务接口
 *
 * @author geekidea
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
