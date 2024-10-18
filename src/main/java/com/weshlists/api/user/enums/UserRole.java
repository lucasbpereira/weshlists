package com.weshlists.api.user.enums;

public enum UserRole {
    ADMIN("admin"),
    GUEST("guest");

    private String role;

    UserRole (String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
