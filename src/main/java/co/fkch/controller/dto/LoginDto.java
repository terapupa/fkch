package co.fkch.controller.dto;

import javax.validation.constraints.NotNull;

public class LoginDto {
    @NotNull(message = "Please provide your user name or email")
    private String userName;
    @NotNull(message = "Please provide your password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
