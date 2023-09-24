package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)
    private String name;

    protected Book() { //Jpa는 기본 생성자가 있어야 함.

    }

    public Book(String name) { //id는 자동생성이기 때문에 이름을 받는
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
