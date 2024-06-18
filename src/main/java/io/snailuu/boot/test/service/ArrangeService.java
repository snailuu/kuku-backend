package io.snailuu.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.test.dto.ArrangeDto;
import io.snailuu.boot.test.entity.Arrange;
import io.snailuu.boot.test.query.ArrangeQuery;
import io.snailuu.boot.test.vo.ArrangeVo;
import io.snailuu.boot.test.query.AppArrangeQuery;
import io.snailuu.boot.test.vo.AppArrangeVo;


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
