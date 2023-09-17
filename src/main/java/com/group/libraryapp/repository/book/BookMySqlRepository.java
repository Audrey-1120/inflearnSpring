package com.group.libraryapp.repository.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
@Primary //컨테이너가 우선적으로 선택함.
@Repository
public class BookMySqlRepository implements BookRepository{

    @Override
    public void saveBook() {
        System.out.println("MySqlRepository");

    }
}
