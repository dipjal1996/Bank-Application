package main.java.com.zeta.assignment.BankApplication.core;


import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String panNumber;

    private List<BankAccount> accounts;

    public Customer(String panNumber) {
        this.panNumber = panNumber;
        this.accounts = new ArrayList<>();
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.accounts.add(bankAccount);
    }

    public List<BankAccount> getBankAccounts() {
        return this.accounts;
    }

    public String getPanNumber() {
        return this.panNumber;
    }

    @Override
    public String toString() {
        return "(" + this.panNumber + " " + this.accounts.toString() + ")";
    }
}
