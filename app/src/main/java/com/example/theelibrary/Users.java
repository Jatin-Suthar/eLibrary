package com.example.theelibrary;

public class Users {
    String id;
    String name;
    String password;
    String email;
    String phone;
    String lastIssue;
    String lastReturn;
    String issueDate;
    String returnDate;

    public Users(){

    }
    public Users(String id, String name, String password, String email, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Users(String id, String lastIssue, String issueDate) {
        this.id = id;
        this.lastIssue = lastIssue;
        this.issueDate = issueDate;
    }

    public Users(String id, String lastReturn, String returnDate, int x) {
        this.id = id;
        this.lastReturn = lastReturn;
        this.returnDate = returnDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastIssue() {
        return lastIssue;
    }

    public void setLastIssue(String lastIssue) {
        this.lastIssue = lastIssue;
    }

    public String getLastReturn() {
        return lastReturn;
    }

    public void setLastReturn(String lastReturn) {
        this.lastReturn = lastReturn;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
