package com.rmsoft.service;

import com.rmsoft.domain.BookVO;
import com.rmsoft.domain.HistoryVO;
import com.rmsoft.mapper.BookMapper;
import com.rmsoft.mapper.HistoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.service
 * @fileName : HistoryService
 * @date : 2023-09-06
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-06        seolha86             최초 생성
 */

@Service
@AllArgsConstructor
public class HistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    private final BookMapper bookMapper;

    public HistoryVO retrieve(Long hisNum) {
        return historyMapper.findByHisNum(hisNum);
    }

    public HistoryVO retrieve(String id, Long bookNum) {
        return historyMapper.findByIdAndBookNum(id, bookNum);
    }

    public List<HistoryVO> history(String id) {
        return historyMapper.findById(id);
    }

    public void borrowBook(String id, Long bookNum) {
        BookVO vo = bookMapper.findByBookNum(bookNum);

        if (vo.getQuantity() >= 1) {
            historyMapper.borrowBook(id, bookNum);
            bookMapper.borrowAndReturn(vo.getBookNum(), vo.getQuantity() - 1);
        }
    }

    public void returnBook(HistoryVO historyVO) {
        BookVO vo = bookMapper.findByBookNum(historyVO.getBookNum());

        historyMapper.returnBook(historyVO);
        bookMapper.borrowAndReturn(vo.getBookNum(), vo.getQuantity() + 1);
    }
}
