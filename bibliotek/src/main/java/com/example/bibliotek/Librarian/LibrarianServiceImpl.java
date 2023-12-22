package com.example.bibliotek.Librarian;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.example.bibliotek.UserApp.Role.LIBRARIAN;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService, UserDetailsService {

    private final LibrarianRepository librarianRepository;

    @Override
    public Librarian saveLibrarian(Librarian librarian) {
        librarian.setRole(LIBRARIAN);
        return librarianRepository.save(librarian);
    }

    @Override
    public void deleteLibrarian(Long id) {
        librarianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Librarian not found"));
        librarianRepository.deleteById(id);
    }

    @Override
    public Librarian updateLibrarian(Long id, Librarian librarian) throws EntityNotFoundException {
        Librarian existingLibrarian = librarianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Librarian not found" + librarian.getFirstname() + " " + librarian.getLastname()));

        existingLibrarian.setFirstname(librarian.getFirstname());
        existingLibrarian.setLastname(librarian.getLastname());
        existingLibrarian.setEmail(librarian.getEmail());
        existingLibrarian.setSocialNumber(librarian.getSocialNumber());
        existingLibrarian.setPassword(librarian.getPassword());
        existingLibrarian.setRole(librarian.getRole());
        existingLibrarian.setIsAccountNonExpired(librarian.getIsAccountNonExpired());
        existingLibrarian.setIsAccountNonLocked(librarian.getIsAccountNonLocked());
        existingLibrarian.setIsCredentialsNonExpired(librarian.getIsCredentialsNonExpired());
        existingLibrarian.setIsEnabled(librarian.getIsEnabled());

        return librarianRepository.save(existingLibrarian);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return librarianRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
    }
}
