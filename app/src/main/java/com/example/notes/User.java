package com.example.notes;

public class User {

    String name;
    String email;
    String contact;
    String password;
    String branch;
    String year;
    String permission;

    public User() {
    }

    public User(String name, String email, String contact, String password, String branch, String year,String permission) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.branch = branch;
        this.year = year;
        this.permission=permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
