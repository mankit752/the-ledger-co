package com.interview.model;

public class Payment {
    private String bankName;
    private String borrowerName;
    private double lumpSumAmount;
    private int lumpSumPaidAfterEmiNumber;

    public Payment(String bankName, String borrowerName, double lumpSumAmount, int lumpSumPaidAfterEmiNumber) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.lumpSumAmount = lumpSumAmount;
        this.lumpSumPaidAfterEmiNumber = lumpSumPaidAfterEmiNumber;
    }

    public String getIdentifier() {
        return bankName + "-" + borrowerName;
    }

    public double getLumpSumAmount() {
        return lumpSumAmount;
    }

    public int getLumpSumPaidAfterEmiNumber() {
        return lumpSumPaidAfterEmiNumber;
    }
}
