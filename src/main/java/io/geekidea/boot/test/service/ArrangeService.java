package io.geekidea.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.test.dto.ArrangeDto;
import io.geekidea.boot.test.entity.Arrange;
import io.geekidea.boot.test.query.ArrangeQuery;
import io.geekidea.boot.test.vo.ArrangeVo;
import io.geekidea.boot.test.query.AppArrangeQuery;
import io.geekidea.boot.test.vo.AppArrangeVo;


/**
 * 值班安排 服务接口
 *
 * @author snailuu
 * @since 2024-06-15
 */
public interface ArrangeService extends IService<Arrange> {

    /**
     * 添加值班安排
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addArrange(ArrangeDto dto);

    /**
     * 修改值班安排
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateArrange(ArrangeDto dto);

    /**
     * 删除值班安排
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteArrange(Long id);

    /**
     * 值班安排详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    ArrangeVo getArrangeById(Long id);

    /**
     * 值班安排分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<ArrangeVo> getArrangePage(ArrangeQuery query);

    /**
     * App值班安排详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    AppArrangeVo getAppArrangeById(Long id);

    /**
     * App值班安排分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<AppArrangeVo> getAppArrangePage(AppArrangeQuery query);

}
