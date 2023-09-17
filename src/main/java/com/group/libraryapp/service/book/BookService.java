package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    } //생성자를 통해 BookRepository를 주입받음


    public void saveBook() {
        bookRepository.saveBook();


    }
}
