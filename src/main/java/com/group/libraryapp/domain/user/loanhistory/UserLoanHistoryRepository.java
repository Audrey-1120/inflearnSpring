package com.group.libraryapp.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoanHistoryRepository extends JpaRepository <UserLoanHistory,Long> {

    //select * from user_loan_history where bookname = ? and isReturn = ?
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

}
