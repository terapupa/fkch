package co.fkch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;

public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    @Transient
    private String password;

    @NotNull(message = "Please provide your first name")
    private String firstName;

    @NotNull(message = "Please provide your last name")
    private String lastName;

    private boolean enabled;

    private String confirmationToken;

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
}
