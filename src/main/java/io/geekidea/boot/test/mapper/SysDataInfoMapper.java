package io.geekidea.boot.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.test.vo.DataInfoVo;
import io.geekidea.boot.test.vo.TicketVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDataInfoMapper extends BaseMapper<DataInfoVo> {

    Integer getTotalTicket();

    Integer getReadyTicket();

    Integer getTotalUser();

    Integer recentFinishTicket(String today);

    Integer recentReadyTicket(String today);

}
