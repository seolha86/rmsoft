package com.rmsoft.controller;

import com.rmsoft.domain.BookVO;
import com.rmsoft.domain.HistoryVO;
import com.rmsoft.domain.ResponseDTO;
import com.rmsoft.service.BookService;
import com.rmsoft.service.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : seolha86
 * @packageName : com.rmsoft.controller
 * @fileName : HistoryController
 * @date : 2023-09-06
 * @description :
 * ===========================================================
 * DATE           AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-06        seolha86             최초 생성
 */

@RestController
@RequestMapping("/history")
@AllArgsConstructor
@Slf4j
public class HistoryController {
    private final HistoryService historyService;
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> historyList(@AuthenticationPrincipal String id) {
        if (id != null) {
            List<HistoryVO> history = historyService.history(id);

            return ResponseEntity.ok().body(history);
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Unauthorized Users").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping
    public ResponseEntity<?> borrowBook(@AuthenticationPrincipal String id, @RequestBody HistoryVO historyVO) {
        if (bookService.findByBookNum(historyVO.getBookNum()).getQuantity() > 0) {
            if (historyService.history(id).size() < 3) {
                BookVO book = bookService.findByBookNum(historyVO.getBookNum());
                historyService.borrowBook(id, book.getBookNum());

                return ResponseEntity.ok().body(book);
            } else {
                ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Can borrow up to three books").build();
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Book is on borrow").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping
    public ResponseEntity<?> returnBook(@AuthenticationPrincipal String id, @RequestBody HistoryVO historyVO) {
        HistoryVO vo = historyService.retrieve(id, historyVO.getBookNum());

        if (vo != null) {
            historyService.returnBook(vo);

            return ResponseEntity.ok().body(vo);
        } else {
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error("Invalid Request").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
