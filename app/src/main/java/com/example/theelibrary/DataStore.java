package com.example.theelibrary;

public class DataStore {
    int numberOfIssue;
    int numberOfReturn;
    String libraryUsername = " ";

    public DataStore(){ }

    public DataStore(int numberOfIssue, int numberOfReturn, String libraryUsername){
        this.numberOfIssue = numberOfIssue;
        this.numberOfReturn = numberOfReturn;
        this.libraryUsername = libraryUsername;
    }

    public int getNumberOfIssue() {
        return numberOfIssue;
    }

    public void setNumberOfIssue(int numberOfIssue) {
        this.numberOfIssue = numberOfIssue;
    }

    public int getNumberOfReturn() {
        return numberOfReturn;
    }

    public void setNumberOfReturn(int numberOfReturn) {
        this.numberOfReturn = numberOfReturn;
    }

    public String getLibraryUsername() {
        return libraryUsername;
    }

    public void setLibraryUsername(String libraryUsername) {
        this.libraryUsername = libraryUsername;
    }
}
