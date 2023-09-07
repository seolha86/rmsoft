package com.rmsoft.service;

import com.rmsoft.domain.BookVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.service
 * @fileName : BookServiceTests
 * @date : 2023-09-05
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-05        seolha86             최초 생성
 */

@SpringBootTest
@Slf4j
public class BookServiceTests {
    @Autowired
    private BookService bookService;

    @DisplayName("도서 전체 조회")
    @Test
    void testRetrieveBook() {
        log.warn(bookService.retrieve().toString());
    }

    // 도서 데이터
//    @DisplayName("도서 더미 데이터")
//    @Test
//    void testBookDownload() {
////        String url = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbksha081459001&QueryType=ItemNewAll&MaxResults=1000&start=1&SearchTarget=Book&output=js&Version=20131101";
//        String url = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbksha081459001&QueryType=Bestseller&MaxResults=1000&start=1&SearchTarget=Book&output=js&Version=20131101";
//
//        RestTemplate restTemplate = new RestTemplate();
//        JsonData response = restTemplate.getForObject(url, JsonData.class);
//
//        assert response != null;
//        List<BookVO> books = response.item;
//
//        log.warn(books.toString());
//
//        for (BookVO vo : books) {
//            BookVO book = new BookVO();
//            book.setTitle(vo.getTitle());
//            book.setAuthor(vo.getAuthor());
//            book.setCategoryId(vo.getCategoryId());
//            book.setPubDate(vo.getPubDate());
//            book.setIsbn(vo.getIsbn());
//
//            bookService.create(book);
//        }
//    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class JsonData {
    String version;
    String link;
    String query;
    List<BookVO> item;
}
