package main.java.com.zeta.assignment.BankApplication.core;

import static main.java.com.zeta.assignment.BankApplication.constant.Constants.CURRENT_ACCOUNT_INTEREST_RATE;
import static main.java.com.zeta.assignment.BankApplication.constant.Constants.CURRENT_ACCOUNT_MINIMUM_BALANCE;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountNumber, Double currentBalance) {
        super(accountNumber, CURRENT_ACCOUNT_MINIMUM_BALANCE, currentBalance, CURRENT_ACCOUNT_INTEREST_RATE);
    }

    @Override
    public BankAccountType getBankAccountType() {
        return BankAccountType.CURRENT;
    }
}
