package com.rmsoft.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.domain
 * @fileName : ResponseDTO
 * @date : 2023-09-01
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-01        seolha86             최초 생성
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private String error;
    private List<T> data;
}
