package com.rmsoft.service;

import com.rmsoft.domain.BookVO;
import com.rmsoft.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.service
 * @fileName : BookServiceImpl
 * @date : 2023-09-01
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-01        seolha86             최초 생성
 */

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public void create(BookVO vo) {
        bookMapper.register(vo);
    }

    public List<BookVO> retrieve() {
        return bookMapper.retrieve();
    }

    public BookVO findByIsbn(String title) {
        return bookMapper.findByIsbn(title);
    }

    public BookVO findByBookNum(Long bookNum) {
        return bookMapper.findByBookNum(bookNum);
    }

    public void update(BookVO bookVO) {
        BookVO vo = bookMapper.findByBookNum(bookVO.getBookNum());

        if (vo != null) {
            vo.setTitle(bookVO.getTitle());
            vo.setAuthor(bookVO.getAuthor());
            vo.setPubdate(bookVO.getPubdate());
            vo.setCategory(bookVO.getCategory());
            vo.setIsbn(bookVO.getIsbn());

            bookMapper.update(vo);
        }
    }
}
