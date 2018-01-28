package co.fkch.exception;

public interface ErrorCode {
    String USER_EXIST = "100";
    String USER_EXIST_CONFIRMED = "101";
    String USER_NOT_FOUND = "102";

    String PASSWORD_MISMATCH = "200";
    String WRONG_PASSWORD = "201";
    String WEAK_PASSWORD = "202";
    String WRONG_CONFIRMATION_TOKEN = "203";
}
