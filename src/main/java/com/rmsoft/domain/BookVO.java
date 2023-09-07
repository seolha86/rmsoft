package com.rmsoft.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.domain
 * @fileName : BookVO
 * @date : 2023-09-01
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-01        seolha86             최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("book")
public class BookVO {
    private Long bookNum;
    private String title;
    private String author;
    private Long category;
    private Date pubdate;
    private String isbn;
    private int quantity;
}
