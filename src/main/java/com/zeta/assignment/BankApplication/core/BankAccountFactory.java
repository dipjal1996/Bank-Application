package main.java.com.zeta.assignment.BankApplication.core;

import main.java.com.zeta.assignment.BankApplication.exception.BankApplicationException;

import static main.java.com.zeta.assignment.BankApplication.constant.Constants.CURRENT_ACCOUNT_MINIMUM_BALANCE;
import static main.java.com.zeta.assignment.BankApplication.constant.Constants.SAVING_ACCOUNT_MINIMUM_BALANCE;

public class BankAccountFactory {

    public static BankAccount createBankAccount(String accountNumber, Double currentBalance, BankAccountType accountType) throws BankApplicationException {

        switch (accountType) {
            case CURRENT:
                if(currentBalance < CURRENT_ACCOUNT_MINIMUM_BALANCE) {
                    throw new BankApplicationException(String.format("Current bank account must have minimum balance %f", CURRENT_ACCOUNT_MINIMUM_BALANCE));
                }
                return new CurrentAccount(accountNumber, currentBalance);
            case SAVING:
                if(currentBalance < SAVING_ACCOUNT_MINIMUM_BALANCE) {
                    throw new BankApplicationException(String.format("Saving bank account must have minimum balance %f", SAVING_ACCOUNT_MINIMUM_BALANCE));
                }
                return new SavingAccount(accountNumber, currentBalance);
            default:
                throw new BankApplicationException(String.format("Bank account type %s not supported.", accountType.toString()));
        }
    }
}
