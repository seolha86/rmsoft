package com.rmsoft.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.domain
 * @fileName : HistoryVO
 * @date : 2023-09-05
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        seolha86             최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("history")
public class HistoryVO {
    private Long hisNum;
    private String id;
    private Long bookNum;
    private Date borrowDate;
    private Date returnDate;
}
