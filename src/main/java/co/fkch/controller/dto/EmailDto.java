package co.fkch.controller.dto;

import javax.validation.constraints.NotNull;

public class EmailDto {
    @NotNull(message = "Please provide your email address")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
