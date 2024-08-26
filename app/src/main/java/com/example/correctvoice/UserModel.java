package com.example.correctvoice;

public class UserModel {
    private static String name;
    private static String email;
    private static String password;

    public UserModel() {
    }

    public UserModel(String name,String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Public getter for name
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    // Public setter for name
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Public getter for password
    public String getPassword() {
        return password;
    }

    // Public setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}
