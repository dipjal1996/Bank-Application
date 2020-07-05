package main.java.com.zeta.assignment.BankApplication.exception;

public class BankApplicationException extends Exception {

    private ErrorEnum errorEnum;

    public BankApplicationException() {
        super();
    }

    public BankApplicationException(String message) {
        super(message);
    }

    public BankApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankApplicationException(ErrorEnum errorEnum) {
        super(errorEnum.getFormattedErrorMessage());
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return this.errorEnum;
    }
}
