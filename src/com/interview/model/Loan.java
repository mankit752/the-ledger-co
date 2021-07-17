package com.interview.model;

public class Loan {
    private String bankName;
    private String borrowerName;
    private double principal;
    private int numberOfYears;
    private double rateOfInterest;
    private double amount;
    private double emi;

    public Loan(String bankName, String borrowerName, double principal, int numberOfYears, double rateOfInterest) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.numberOfYears = numberOfYears;
        this.rateOfInterest = rateOfInterest;
        this.amount = getTotalAmount(principal, numberOfYears, rateOfInterest);
        this.emi = calculateEmi(amount, numberOfYears);
    }

    public String getIdentifier() {
        return bankName + "-" + borrowerName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public double getAmount() {
        return amount;
    }

    public double getEmi() {
        return emi;
    }

    private double getTotalAmount (double principal, int term, double rate) {
        return  principal + Math.ceil(principal * term * (rate / 100));
    }

    private double calculateEmi(double amount, int term) {
        return Math.ceil(amount / (term * 12));
    }
}
