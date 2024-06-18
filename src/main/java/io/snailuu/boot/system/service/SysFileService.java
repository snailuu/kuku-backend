package io.snailuu.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.snailuu.boot.framework.page.Paging;
import io.snailuu.boot.system.dto.SysFileDto;
import io.snailuu.boot.system.entity.SysFile;
import io.snailuu.boot.system.query.SysFileQuery;
import io.snailuu.boot.system.vo.SysFileVo;


/**
 * 系统文件 服务接口
 *
 * @author snailuu
 * @since 2023-11-26
 */
public interface SysFileService extends IService<SysFile> {

    /**
     * 修改系统文件
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysFile(SysFileDto dto);

    /**
     * 删除系统文件
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysFile(Long id);

    /**
     * 系统文件详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysFileVo getSysFileById(Long id);

    /**
     * 系统文件分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysFileVo> getSysFilePage(SysFileQuery query);

}
