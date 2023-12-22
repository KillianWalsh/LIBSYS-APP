package com.example.bibliotek.Bookloans;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookloans", produces = "application/json; charset=UTF-8")

public class BookLoansController {

    private final BookLoanService bookLoanService;

    @PostMapping("/save")
    public void save(@RequestParam Long borrowerId, Long bookId) {
        bookLoanService.save(borrowerId, bookId);
    }

    //hämtar alla lån som en låntagare har
    @GetMapping("/findallbyid")
    public List<BookLoans> findAllById(Long id) {
        return bookLoanService.findAllById(id);
    }

    //Hämtar ALLA lån
    @GetMapping("/findall")
    public List<BookLoans> findAll() {
        return bookLoanService.findAll();
    }

    //Hämtar de 100 senaste med absolut senaste först
    @GetMapping("/findlatest")
    public List<BookLoans> findLatest() {
        return bookLoanService.findLatest();
    }

    //Hämtar alla lån idag
    @GetMapping("/loanstoday")
    public List<BookLoans> loansToday() {
        return bookLoanService.loansToday();
    }

    //Hämtar alla lån senaste veckan från idag
    @GetMapping("/loanslatestweek")
    public List<BookLoans> loansWeek() {
        return bookLoanService.loansWeek();
    }

    //Hämtar alla lån för en viss månad och då menar jag för t ex mars(3) och utgår alltså inte från idag
    //Skicka med intar för den år och månad, dvs år = 2022, mars = 3
    @GetMapping("/loansbymonth")
    public List<BookLoans> loansMonth(int year, int month) {
        return bookLoanService.loansMonth(year, month);
    }

    //Hämtar SENA lån
    @GetMapping("/lateloans")
    public List<BookLoans> lateLoans() {
        return bookLoanService.lateLoans();
    }

    //Hämtar alla lån av en viss titel
    @GetMapping("/allloansoftitle")
    public List<BookLoans> allLoansOfTitle(String title) {
        return bookLoanService.allLoansOfTitle(title);
    }

}
