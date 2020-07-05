package main.java.com.zeta.assignment.BankApplication.exception;

public enum ErrorEnum {

    INVALID_WITHDRAW_AMOUNT(1001, "Withdraw amount should be a non negative value."),

    INSUFFICIENT_BALANCE(1002, "Insufficient balance in the account to perform the transaction."),

    INVALID_DEPOSIT_AMOUNT(1003, "Deposit amount must be a non negative value."),

    CUSTOMER_DOES_NOT_EXIST(1004, "Customer with the given pan number does not hold an account in this branch."),

    ACCOUNT_NUMBER_DOES_NOT_EXIST(1005, "Bank account with the given account number does not exist in this branch."),

    BRANCH_DOES_NOT_EXIST(1006, "Branch with the given branch id does not exist.");

    private int errorCode;
    private String message;

    ErrorEnum(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getFormattedErrorMessage() {
        return String.format("error code: %s, message: %s", this.errorCode, this.message);
    }
}
