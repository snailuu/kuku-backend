package io.geekidea.boot.test.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.test.mapper.SysDataInfoMapper;
import io.geekidea.boot.test.service.DataInfoService;
import io.geekidea.boot.test.vo.DataInfoVo;
import io.geekidea.boot.test.vo.TicketListVo;
import io.geekidea.boot.test.vo.TicketVo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInfoServiceImpl extends ServiceImpl<SysDataInfoMapper, DataInfoVo> implements DataInfoService {

    @Override
    public DataInfoVo getDataInfo() {
        DataInfoVo dataInfoVo = new DataInfoVo();
        dataInfoVo.setTotalTicket(this.baseMapper.getTotalTicket());
        dataInfoVo.setTotalReadyTicket(this.baseMapper.getReadyTicket());
        dataInfoVo.setTotalUser(this.baseMapper.getTotalUser());
        List<Integer> finishList = new ArrayList<>();
        List<Integer> unFinishList = new ArrayList<>();
        List<String> RecentDateList = getRecentDate();
        for(int i = 0; i < 7; i++){
            finishList.add(this.baseMapper.recentFinishTicket(RecentDateList.get(i)));
        }
        for(int i = 0; i < 7; i++){
            unFinishList.add(this.baseMapper.recentReadyTicket(RecentDateList.get(i)));
        }
        List<TicketListVo> list = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            list.add(new TicketListVo(RecentDateList.get(i), finishList.get(i), unFinishList.get(i)));
        }
        dataInfoVo.setRecentTicket(list);
        return dataInfoVo;
    }


    List<String> getRecentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 获取今天的日期
        LocalDate today = LocalDate.now();

        // 创建一个列表来存储近7天的日期字符串
        List<String> last7Days = new ArrayList<>();

        // 循环获取近7天的日期，并格式化为字符串
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String formattedDate = date.format(formatter);
            last7Days.add(formattedDate);
        }

        return last7Days;
    }
}
