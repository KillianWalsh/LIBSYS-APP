package com.example.bibliotek.Borrower;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/borrower", produces = "application/json; charset=UTF-8")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping("/save/{json}")
    public void save(@PathVariable String json) {
        //System.out.println(json);
        Gson gson = new Gson();
        Borrower borrower = gson.fromJson(json, Borrower.class);
        borrowerService.save(borrower);
    }

//    Använder den här med generated-request, fungerar inte med ovanstående
//    @PostMapping("/save")
//    public void save(@RequestBody String json){
//        Gson gson = new Gson();
//        Borrower borrower = gson.fromJson(json, Borrower.class);
//        borrowerService.save(borrower);
//    }

//    @PostMapping("/save")
//    public ResponseEntity<Borrower> save(@RequestBody Borrower borrower){
//       return new ResponseEntity<>(borrowerService.save(borrower), HttpStatus.CREATED);
//    }

    //doesn't send the id (which is important for frontend)
    @GetMapping("/findbyemail")
    @ResponseBody
    public Borrower findByEmail(@RequestParam String email) {
        return borrowerService.findByEmail(email);
    }

    @GetMapping("/findbyId")
    @ResponseBody
    public Borrower findById(@RequestParam Long id) {
        return borrowerService.findById(id);
    }

    @GetMapping("/getAll")
    public ArrayList<Borrower> findAll() {
        return borrowerService.findAll();
    }


    @PostMapping("/register")
    public ResponseEntity<Borrower> register(@RequestBody Borrower borrower) {
        return new ResponseEntity<>(borrowerService.register(borrower), HttpStatus.CREATED);
    }

}
