package com.rmsoft.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.core.annotation.AliasFor;

import java.util.Date;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.domain
 * @fileName : MemberVO
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
@Alias("member")
public class MemberVO {
    private String id;
    private String pw;
    private String name;
    private Date regDate;
}
