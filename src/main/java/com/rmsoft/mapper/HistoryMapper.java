package com.rmsoft.mapper;

import com.rmsoft.domain.HistoryVO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.mapper
 * @fileName : HistoryMapper
 * @date : 2023-09-06
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-06        seolha86             최초 생성
 */

@Mapper
public interface HistoryMapper {
    void borrowBook(String id, Long bookNum);
    void returnBook(HistoryVO historyVO);

    // 대출 중인 책 리스트
    List<HistoryVO> findById(String id);
    HistoryVO findByHisNum(Long hisNum);
    HistoryVO findByIdAndBookNum(String id, Long bookNum);
}
