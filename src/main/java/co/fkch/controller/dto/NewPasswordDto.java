package co.fkch.controller.dto;

import javax.validation.constraints.NotNull;

public class NewPasswordDto {
    @NotNull(message = "Please provide your password")
    private String password;
    @NotNull(message = "Please retype your password")
    private String repeat;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
}
