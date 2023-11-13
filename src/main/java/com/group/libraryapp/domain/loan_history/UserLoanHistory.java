package com.group.libraryapp.domain.loan_history;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private Long user_id;
    private String bookName;
    private boolean isReturn;

    public UserLoanHistory() {
    }

    public UserLoanHistory(Long user_id, String bookName) {
        this.user_id = user_id;
        this.bookName = bookName;
        this.isReturn = false;
    }
}
