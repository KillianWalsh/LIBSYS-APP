package com.example.bibliotek.Admin;

import com.example.bibliotek.UserApp.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.example.bibliotek.LoginUser.Role.ADMIN;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(Admin admin) {
        admin.setRole(ADMIN);
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        adminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) throws EntityNotFoundException {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        existingAdmin.setFirstname(admin.getFirstname());
        existingAdmin.setLastname(admin.getLastname());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setSocialNumber(admin.getSocialNumber());
        existingAdmin.setPassword(admin.getPassword());
        existingAdmin.setRole(admin.getRole());
        existingAdmin.setIsAccountNonExpired(admin.getIsAccountNonExpired());
        existingAdmin.setIsAccountNonLocked(admin.getIsAccountNonLocked());
        existingAdmin.setIsCredentialsNonExpired(admin.getIsCredentialsNonExpired());
        existingAdmin.setIsEnabled(admin.getIsEnabled());

        return adminRepository.save(existingAdmin);
    }
}
