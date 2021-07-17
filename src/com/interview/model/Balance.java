package com.interview.model;

public class Balance {
    private String bankName;
    private String borrowerName;
    private int totalAmountPaid;
    private int numberOfEmiRemaining;

    public Balance(String bankName, String borrowerName, int totalAmountPaid, int numberOfEmiRemaining) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.totalAmountPaid = totalAmountPaid;
        this.numberOfEmiRemaining = numberOfEmiRemaining;
    }

    @Override
    public String toString() {
        return bankName + " " + borrowerName + " " + totalAmountPaid + " " + numberOfEmiRemaining;
    }
}
