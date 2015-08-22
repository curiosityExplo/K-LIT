package com.example.iidea8.k_lit;

/**
 * Created by Abhigyan on 8/19/2015.
 */
public class User {
    String name; String email;  String password;
    int contact;


    public User(String name, String email, String password, int contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public User(String email, String password) {
        this.name = "";
        this.email = email;
        this.password = password;
        this.contact = 0;
    }
}
