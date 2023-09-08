package com.rmsoft.controller;

import com.rmsoft.domain.BookVO;
import com.rmsoft.domain.ResponseDTO;
import com.rmsoft.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.controller
 * @fileName : BookController
 * @date : 2023-09-01
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-01        seolha86             최초 생성
 */

@RestController
@RequestMapping("/book/")
@AllArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> retrieve(@AuthenticationPrincipal String id) {
        if (id != null) {
            List<BookVO> bookList = bookService.retrieve();
            log.warn(bookList.toString());

            return ResponseEntity.ok().body(bookList);
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Unauthorized Users").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal String id, @RequestBody BookVO bookVO) {
        if (id != null && bookService.findByIsbn(bookVO.getIsbn()) == null) {
            bookService.create(bookVO);

            return ResponseEntity.ok().body(bookVO);
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Book Already Existed").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@AuthenticationPrincipal String id, @RequestBody BookVO bookVO) {
        if (id != null && bookVO != null) {
            bookService.update(bookVO);

            return ResponseEntity.ok().body(bookVO);
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Invalid Request").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
