package test.java.com.zeta.assignment.BankApplication;

import main.java.com.zeta.assignment.BankApplication.core.*;
import main.java.com.zeta.assignment.BankApplication.exception.BankApplicationException;
import main.java.com.zeta.assignment.BankApplication.exception.ErrorEnum;

import java.util.List;

public class BankApplicationTest {

    public static void main(String[] args) throws Exception {
        BankApplicationTest bankApplication = new BankApplicationTest();
        bankApplication.run();
    }

    private void run() throws Exception {
        HeadOffice headOffice = new HeadOffice();

        headOffice.createBranch();
        headOffice.createBranch();
        headOffice.createBranch();

        Branch firstBranch = headOffice.getBranchById("1");
        System.out.println(firstBranch.toString());

        List<Branch> branches = headOffice.getAllBranches();
        assert(3 == branches.size());

        Branch branch = branches.get(0);
        branch.createBankAccount("123", BankAccountType.SAVING, 15000.0);
        branch.createBankAccount("456", BankAccountType.CURRENT, 20000.0);
        branch.createBankAccount("abc", BankAccountType.SAVING, 30000.0);
        branch.createBankAccount("123", BankAccountType.CURRENT, 40000.0);

        System.out.println("Fetching customer by valid pan number");
        Customer customer = branch.getCustomerByPanNumber("123");

        System.out.println("Printing all the bank accounts of this customer.");
        for(BankAccount bankAccount : customer.getBankAccounts()) {
            System.out.println(bankAccount.toString());
        }

        System.out.println("pan number: " + customer.getPanNumber());


        System.out.println("----------------------------");

        try {
            System.out.println("Fetching customer by invalid pan number -  must throw error");
            branch.getCustomerByPanNumber("invalid-pan-number");
        } catch (BankApplicationException bae) {
            if(!bae.getErrorEnum().equals(ErrorEnum.CUSTOMER_DOES_NOT_EXIST)) {
                throw new AssertionError("Error thrown should be customer does not exist.");
            }
        }

        System.out.println("Fetching valid bank accounts by account number");
        for(int i = 1; i <= 4; ++i) {
            System.out.println(branch.getAccountByAccountNumber(String.valueOf(i)).toString());
        }

        System.out.println("Fetching bank account by invalid account number - must throw error");
        try {
            branch.getAccountByAccountNumber("0");
        } catch (BankApplicationException bae) {
            if(!bae.getErrorEnum().equals(ErrorEnum.ACCOUNT_NUMBER_DOES_NOT_EXIST)) {
                throw new AssertionError("Error thrown should be account number does not exist");
            }
        }

        try {
            branch.getAccountByAccountNumber("5");
        } catch (BankApplicationException bae) {
            if(!bae.getErrorEnum().equals(ErrorEnum.ACCOUNT_NUMBER_DOES_NOT_EXIST)) {
                throw new AssertionError("Error thrown should be account number does not exist");
            }
        }

        System.out.println("Branch id: " + branch.getBranchId());

        BankAccount bankAccount = branch.getAccountByAccountNumber("4");
        System.out.println("Current balance: " + bankAccount.getCurrentBalance());

        bankAccount.withdraw(10000.0);
        System.out.println(String.format("After withdrawing amount %s, current balance is %s", 10000.0, bankAccount.getCurrentBalance()));

        Double currentBal = bankAccount.getCurrentBalance();

        //After this transaction, the balance will be less than minimum balance required in current account - so the transaction will fail.
        try {
            bankAccount.withdraw(15000.0);
        } catch (BankApplicationException bae) {
            if(!bae.getErrorEnum().equals(ErrorEnum.INSUFFICIENT_BALANCE)) {
                throw new AssertionError("Error thrown should be insufficient balance.");
            }
        }

        //verifying that the current balance is unchanged.
        System.out.println("Current balance is: " + bankAccount.getCurrentBalance());
        if(!bankAccount.getCurrentBalance().equals(currentBal)) {
            throw new AssertionError("Balance should not have been changed.");
        }

        currentBal = bankAccount.getCurrentBalance();

        //withdrawing negative amount - transaction must fail.
        try {
            bankAccount.withdraw(-100.0);
        } catch (BankApplicationException bae) {
            if(!bae.getErrorEnum().equals(ErrorEnum.INVALID_WITHDRAW_AMOUNT)) {
                throw new AssertionError("Error thrown should be - Withdraw amount must be a non negative value.");
            }
        }

        //Depositing some amount
        bankAccount.deposit(50000.0);
        System.out.println(String.format("After depositing amount %s, current balance is %s", 50000.0, bankAccount.getCurrentBalance()));

        //verifying that the amount has been deposited successfully
        System.out.println("Current balance is " + bankAccount.getCurrentBalance());
        if(!bankAccount.getCurrentBalance().equals(currentBal + 50000.0)) {
            throw new AssertionError("Current balance not as expected.");
        }

        //Depositing negative amount - transaction must fail.
        try {
            bankAccount.deposit(-200.0);
        } catch (BankApplicationException bae) {
            if(!bae.getErrorEnum().equals(ErrorEnum.INVALID_DEPOSIT_AMOUNT)) {
                throw new AssertionError("Error thrown should be - Deposit amount must be a non negative value.");
            }
        }


        List<Transaction> transactionHistory = bankAccount.getTransactionHistory();

        System.out.println("Printing the complete transaction history");
        for(Transaction transaction : transactionHistory) {
            System.out.println(transaction.toString());
        }

        System.out.println("Printing the mini statement");
        for(Transaction transaction : bankAccount.getMiniStatement()) {
            System.out.println(transaction.toString());
        }

        System.out.println("Bank account type: " + bankAccount.getBankAccountType());
        System.out.println("Minimum balance required: " + bankAccount.getMinimumBalance());
        System.out.println("Current balance: " + bankAccount.getCurrentBalance());
        System.out.println("Interest rate: " + bankAccount.getInterestRate());
    }
}
