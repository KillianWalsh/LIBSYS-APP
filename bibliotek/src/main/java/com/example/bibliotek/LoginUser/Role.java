package com.example.bibliotek.LoginUser;

public enum Role {
    ADMIN("ADMIN"),
    LIBRARIAN("LIBRARIAN"),
    BORROWER("BORROWER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

