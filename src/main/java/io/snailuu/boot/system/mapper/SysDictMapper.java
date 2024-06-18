package io.snailuu.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.snailuu.boot.system.entity.SysDict;
import io.snailuu.boot.system.query.SysDictQuery;
import io.snailuu.boot.system.vo.SysDictVo;
import io.snailuu.boot.system.query.SysDictAppQuery;
import io.snailuu.boot.system.vo.AppSysDictVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据 Mapper 接口
 *
 * @author snailuu
 * @since 2023-11-25
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 字典数据详情
     *
     * @param id
     * @return
     */
    SysDictVo getSysDictById(Long id);

    /**
     * 字典数据分页列表
     *
     * @param query
     * @return
     */
    List<SysDictVo> getSysDictPage(SysDictQuery query);

    /**
     * App字典数据列表
     *
     * @param query
     * @return
     */
    List<AppSysDictVo> getAppSysDictList(SysDictAppQuery query);

}
