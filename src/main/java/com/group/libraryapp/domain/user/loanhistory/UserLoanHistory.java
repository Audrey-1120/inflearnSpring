package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    private User user;
    private String bookName;
    private boolean isReturn;

    protected UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){ //대출기록에 대한 return 작업 진행
        this.isReturn = true; //대출을 반납상태로 변경
    }

    public String getBookName() {
        return this.bookName;
    } //책 제목 가져오는 함수 생성
}
