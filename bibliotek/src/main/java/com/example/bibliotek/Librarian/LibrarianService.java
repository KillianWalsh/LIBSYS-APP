package com.example.bibliotek.Librarian;

public interface LibrarianService {

    Librarian saveLibrarian(Librarian librarian);

    void deleteLibrarian(Long id);

    Librarian updateLibrarian(Long id, Librarian librarian);

}
