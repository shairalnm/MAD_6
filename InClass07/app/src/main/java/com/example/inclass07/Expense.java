package com.example.inclass07;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    private String ExpenseName;
    private String Category;
    private String Amount;
    private String date;

    public String getExpenseName() {
        return ExpenseName;
    }

    public void setExpenseName(String expenseName) {
        ExpenseName = expenseName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "ExpenseName='" + ExpenseName + '\'' +
                ", Category='" + Category + '\'' +
                ", Amount='" + Amount + '\'' +
                ", date=" + date +
                '}';
    }
}
