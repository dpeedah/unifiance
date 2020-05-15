package com.example.unifinancetracker;

public class Expense {
    public int id;
    public  String category;
    public  int amount;
    public  String datetime;

    Expense(int id, String category, int amount, String datetime){
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.datetime = datetime;
    }
}
