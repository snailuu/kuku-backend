package io.snailuu.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.snailuu.boot.system.entity.SysFile;
import io.snailuu.boot.system.query.SysFileQuery;
import io.snailuu.boot.system.vo.SysFileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统文件 Mapper 接口
 *
 * @author snailuu
 * @since 2023-11-26
 */
@Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

    /**
     * 系统文件详情
     *
     * @param id
     * @return
     */
    SysFileVo getSysFileById(Long id);

    /**
     * 系统文件分页列表
     *
     * @param query
     * @return
     */
    List<SysFileVo> getSysFilePage(SysFileQuery query);

}
