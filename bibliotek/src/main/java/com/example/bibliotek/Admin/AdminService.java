package com.example.bibliotek.Admin;

public interface AdminService {

    Admin saveAdmin(Admin admin);

    void deleteAdmin(Long id);

    Admin updateAdmin(Long id, Admin admin);
}
