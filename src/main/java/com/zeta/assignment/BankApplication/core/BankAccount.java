package main.java.com.zeta.assignment.BankApplication.core;

import main.java.com.zeta.assignment.BankApplication.exception.BankApplicationException;
import main.java.com.zeta.assignment.BankApplication.exception.ErrorEnum;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {

    protected String accountNumber;

    protected Double minimumBalance;

    protected Double currentBalance;

    protected Double interestRate;

    protected List<Transaction> transactions;

    protected BankAccount(String accountNumber, Double minimumBalance, Double currentBalance, Double interestRate) {
        this.accountNumber = accountNumber;
        this.minimumBalance = minimumBalance;
        this.currentBalance = currentBalance;
        this.interestRate = interestRate;
        this.transactions = new ArrayList<>();
    }

    public void withdraw(Double amount) throws BankApplicationException {
        if(amount < 0.0) {
            throw new BankApplicationException(ErrorEnum.INVALID_WITHDRAW_AMOUNT);
        }
        if((this.currentBalance - amount) < minimumBalance) {
            throw new BankApplicationException(ErrorEnum.INSUFFICIENT_BALANCE);
        }
        this.currentBalance -= amount;

        String currentTransactionId = String.valueOf(1 + transactions.size());
        this.transactions.add(new Transaction(currentTransactionId, amount, TransactionType.WITHDRAW));
    }

    public void deposit(Double amount) throws BankApplicationException {
        if(amount < 0.0) {
            throw new BankApplicationException(ErrorEnum.INVALID_DEPOSIT_AMOUNT);
        }
        this.currentBalance += amount;

        String currentTransactionId = String.valueOf(1 + transactions.size());
        this.transactions.add(new Transaction(currentTransactionId, amount, TransactionType.DEPOSIT));
    }

    public Double getInterestRate() {
        return this.interestRate;
    }

    public Double getMinimumBalance() {
        return this.minimumBalance;
    }

    public abstract BankAccountType getBankAccountType();

    public Double getCurrentBalance() {
        return this.currentBalance;
    }

    public List<Transaction> getTransactionHistory() {
        return this.transactions;
    }

    public List<Transaction> getMiniStatement() {
        List<Transaction> latestTransactions = new ArrayList<>();
        int totalTransactions = this.transactions.size();
        for(int i = Math.max(0, totalTransactions - 9); i < totalTransactions; ++i) {
            latestTransactions.add(transactions.get(i));
        }
        return latestTransactions;
    }

    @Override
    public String toString() {
        return "(" + this.getBankAccountType() + " " + this.accountNumber + " " + this.minimumBalance + " " + this.currentBalance + " " + this.interestRate +
                " " + this.transactions.toString() + ")";
    }
}
