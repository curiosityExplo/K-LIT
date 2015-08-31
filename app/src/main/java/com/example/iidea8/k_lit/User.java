package com.example.iidea8.k_lit;

/**
 * Created by Abhigyan on 8/19/2015.
 */
public class User {
    String name;
    String email;
    String password;
    int correctAnswers;



    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.name = "";
        this.email = email;
        this.password = password;
    }
    public User(String name, String email, int correctAnswers){
        this.name = name;
        this.email = email;
        this.correctAnswers = correctAnswers;
    }
}
