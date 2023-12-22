package com.example.bibliotek.Bookloans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class BookLoans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long borrowerId;
    private Long bookId;
    private LocalDate dateOfBookLoanDay;
    private LocalDate dateOfBookReturn;
    private String title;

    public BookLoans(Long borrowerId, Long bookId) {
        this.borrowerId = borrowerId;
        this.bookId = bookId;
    }

    public BookLoans() {
    }

    public long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getDateOfBookLoanDay() {
        return dateOfBookLoanDay;
    }

    public void setDateOfBookLoanDay(LocalDate today) {
        dateOfBookLoanDay = today;
    }

    public LocalDate getDateOfBookReturn() {
        return dateOfBookReturn;
    }

    public void setDateOfBookReturn(LocalDate loanPeriod) {
        dateOfBookReturn = loanPeriod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookLoans{" +
                "id=" + id +
                ", borrowerId=" + borrowerId +
                ", bookId=" + bookId +
                ", dateOfBookLoanDay=" + dateOfBookLoanDay +
                ", dateOfBookReturn=" + dateOfBookReturn +
                '}';
    }
}
