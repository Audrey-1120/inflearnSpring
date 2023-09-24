package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    } //생성자를 통해 BookRepository를 주입받음


    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName())); //이름을 넣어서 Book 객체 새로 만듬

    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        // 1. 책 정보를 가져온다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출기록 정보를 확인해서 대출중인지 확인한다.
        // 3. 만약에 확인했는데 대출 중이라면 예외를 발생시킨다.
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("이미 다른 유저가 대출한 도서입니다.");
        }

        // 4. 유저 정보 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());


    }

    @Transactional
    public void returnBook(BookReturnRequest request){

        // 1. 유저 정보를 받아서 유저의 id 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 받아온 유저 Id와 입력한 책 제목을 매개변수로 받아 대출 기록이 있는지에 대한 정보를 가져온다.
        user.returnBook(request.getBookName());

    }




}
