package com.example.bibliotek.Librarian;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/libsys/management")
@RestController
public class LibrarianController {

    private final LibrarianService librarianService;

    @PostMapping("/save")
    public ResponseEntity<Librarian> saveLibrarian(@RequestBody Librarian librarian) {
        return new ResponseEntity<>(librarianService.saveLibrarian(librarian), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Librarian> updateLibrarian(@PathVariable("id") Long id, @RequestBody Librarian librarian) {
        return new ResponseEntity<>(librarianService.updateLibrarian(id, librarian), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteLibrarian(@PathVariable("id") Long id) {
        librarianService.deleteLibrarian(id);
        return new ResponseEntity<>("Librarian successfully deleted", HttpStatus.OK);
    }

}
