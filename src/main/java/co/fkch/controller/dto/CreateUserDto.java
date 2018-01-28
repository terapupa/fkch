package co.fkch.controller.dto;

import javax.validation.constraints.NotNull;

public class CreateUserDto extends EmailDto {
    @NotNull(message = "Please provide your first name")
    private String firstName;
    @NotNull(message = "Please provide your last name")
    private String lastName;
    @NotNull(message = "Please provide your user name")
    private String userName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
