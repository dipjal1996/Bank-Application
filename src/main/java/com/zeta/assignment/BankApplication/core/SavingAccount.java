package main.java.com.zeta.assignment.BankApplication.core;

import static main.java.com.zeta.assignment.BankApplication.constant.Constants.SAVING_ACCOUNT_INTEREST_RATE;
import static main.java.com.zeta.assignment.BankApplication.constant.Constants.SAVING_ACCOUNT_MINIMUM_BALANCE;

public class SavingAccount extends BankAccount {

    public SavingAccount(String accountNumber, Double currentBalance) {
        super(accountNumber, SAVING_ACCOUNT_MINIMUM_BALANCE, currentBalance, SAVING_ACCOUNT_INTEREST_RATE);
    }

    @Override
    public BankAccountType getBankAccountType() {
        return BankAccountType.SAVING;
    }
}
