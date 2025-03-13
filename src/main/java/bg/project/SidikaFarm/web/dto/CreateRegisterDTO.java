package bg.project.SidikaFarm.web.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class CreateRegisterDTO {
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String password;
    private String confirmPassword;
    private LocalDateTime registeredOn;

    public CreateRegisterDTO() {
        setRegisteredOn();
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


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn() {
        this.registeredOn = LocalDateTime.now();
    }
}
