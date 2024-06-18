package io.snailuu.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.snailuu.boot.test.vo.DataInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDataInfoMapper extends BaseMapper<DataInfoVo> {

    Integer getTotalTicket();

    Integer getReadyTicket();

    Integer getTotalUser();

    Integer recentFinishTicket(String today);

    Integer recentReadyTicket(String today);

}
