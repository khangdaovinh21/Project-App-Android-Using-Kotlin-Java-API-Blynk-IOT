package com.example.firetruck3;

public class ContributionItem {
    private String name;
    private String phone;
    private double amount;
    private double shares;

    // Constructor
    public ContributionItem(String name, String phone, double amount, double shares) {
        this.name = name;
        this.phone = phone;
        this.amount = amount;
        this.shares = shares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getShares() {
        return shares;
    }

    public void setShares(double shares) {
        this.shares = shares;
    }
}
