package io.geekidea.boot.test.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketListVo {
    String date;
    Integer finish;
    Integer unFinish;
}
