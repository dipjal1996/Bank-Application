package main.java.com.zeta.assignment.BankApplication.core;

import main.java.com.zeta.assignment.BankApplication.exception.BankApplicationException;
import main.java.com.zeta.assignment.BankApplication.exception.ErrorEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {

    private List<BankAccount> bankAccounts;

    private List<Customer> customers;

    private String branchId;

    private Map<String, Customer> panNumberToCustomerMapping;

    public Branch(String branchId) {
        this.branchId = branchId;
        this.bankAccounts = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.panNumberToCustomerMapping = new HashMap<>();
    }

    public void createBankAccount(String panNumber, BankAccountType accountType, Double amount) throws BankApplicationException {
        String currentBankAccountNumber = String.valueOf(1 + bankAccounts.size());
        BankAccount newBankAccount = BankAccountFactory.createBankAccount(currentBankAccountNumber, amount, accountType);
        if(this.panNumberToCustomerMapping.containsKey(panNumber)) {
            Customer customer = this.panNumberToCustomerMapping.get(panNumber);
            customer.addBankAccount(newBankAccount);
        } else {
            Customer customer = new Customer(panNumber);
            customer.addBankAccount(newBankAccount);
            this.addNewCustomer(customer);
        }
        this.addNewBankAccount(newBankAccount);
    }

    public Customer getCustomerByPanNumber(String panNumber) throws BankApplicationException {
        if(!panNumberToCustomerMapping.containsKey(panNumber)) {
            throw new BankApplicationException(ErrorEnum.CUSTOMER_DOES_NOT_EXIST);
        }
        return this.panNumberToCustomerMapping.get(panNumber);
    }

    public BankAccount getAccountByAccountNumber(String accountNumber) throws BankApplicationException {
        Integer accountNumberIndex = Integer.valueOf(accountNumber);
        if(accountNumberIndex < 1 || accountNumberIndex > bankAccounts.size()) {
            throw new BankApplicationException(ErrorEnum.ACCOUNT_NUMBER_DOES_NOT_EXIST);
        }
        return this.bankAccounts.get(accountNumberIndex - 1);
    }

    private void addNewCustomer(Customer customer) {
        this.customers.add(customer);
        this.panNumberToCustomerMapping.put(customer.getPanNumber(), customer);
    }

    private void addNewBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

    public String getBranchId() {
        return this.branchId;
    }


    @Override
    public String toString() {
        return "(" + this.branchId + " " + this.bankAccounts.toString() + " " + this.customers.toString() + ")";
    }
}
