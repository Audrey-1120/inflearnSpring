package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    } //생성자를 통해 컨테이너가 대신 BookService를 넣어줌.

    @PostMapping("/book")
    public void saveBook(@RequestBody BookCreateRequest request) {
        bookService.saveBook(request);
    }

    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest request){
        bookService.loanBook(request);
    }

    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request){
        bookService.returnBook(request);
    }


}

