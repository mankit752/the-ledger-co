package com.interview;

import com.interview.model.Balance;
import com.interview.model.Loan;
import com.interview.model.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Geektrust {

    private final static String LOAN = "LOAN";
    private final static String BALANCE = "BALANCE";
    private final static String PAYMENT = "PAYMENT";

    private static HashMap<String, Loan> loanMap = new HashMap<>();
    private static HashMap<String, List<Payment>> paymentMap = new HashMap<>();

    public static void main(String[] args) {
        String filePath = args[0];
        List<String> list;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            list = br.lines().collect(Collectors.toList());
            for (int i = 0; i < list.size(); i++) {
                String[] inputArray = list.get(i).toUpperCase().split(" ");
                switch (inputArray[0]) {
                    case LOAN:
                        populateLoan(inputArray);
                        break;

                    case PAYMENT:
                        populatePayment(inputArray);
                        break;

                    case BALANCE:
                        calculateBalance(inputArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculateBalance(String[] inputArray) {
        String identifier = inputArray[1] + "-" + inputArray[2];
        int emiNumberToCheckAfter = Integer.parseInt(inputArray[3]);
        Loan loan = loanMap.get(identifier);
        if (loan == null) {
            System.out.println("There is no loan for the Customer = " + loan.getBorrowerName() + "from Bank = " + loan.getBankName());
            return;
        } else {
            double emiPaid = loan.getEmi() * emiNumberToCheckAfter;
            List<Payment> paymentList = paymentMap.getOrDefault(identifier, new ArrayList<>());
            int lumpSumPaid = 0;
            for (Payment payment : paymentList) {
                if (emiNumberToCheckAfter >= payment.getLumpSumPaidAfterEmiNumber()) {
                    lumpSumPaid += payment.getLumpSumAmount();
                }
            }
            int totalAmountPaid = (int) (emiPaid + lumpSumPaid);
            double balanceAmount = loan.getAmount() - totalAmountPaid;
            int numberOfEmiPending = (int) Math.ceil(balanceAmount / loan.getEmi());
            Balance balance = new Balance(inputArray[1], inputArray[2], totalAmountPaid, numberOfEmiPending);
            System.out.println(balance.toString());
        }
    }

    private static void populatePayment(String[] inputArray) {
        Payment payment = new Payment(inputArray[1], inputArray[2], Double.parseDouble(inputArray[3])
                , Integer.parseInt(inputArray[4]));
        List<Payment> paymentList = paymentMap.getOrDefault(payment.getIdentifier(), new ArrayList<>());
        paymentList.add(payment);
        paymentMap.put(payment.getIdentifier(), paymentList);
    }

    private static void populateLoan(String[] caseInput) {
        Loan loan = new Loan(caseInput[1], caseInput[2], Double.parseDouble(caseInput[3])
                , Integer.parseInt(caseInput[4]), Double.parseDouble(caseInput[5]));
        String identifier = loan.getIdentifier();
        loanMap.put(identifier, loan);
    }
}
