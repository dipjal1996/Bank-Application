package main.java.com.zeta.assignment.BankApplication.core;

public class Transaction {

    private String transactionId;

    private Double amount;

    private TransactionType type;

    public Transaction(String transactionId, Double amount, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = transactionType;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public Double getAmount() {
        return this.amount;
    }

    public TransactionType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "(" + this.transactionId + " " + this.amount + " " + this.type.name() + ")";
    }
}
