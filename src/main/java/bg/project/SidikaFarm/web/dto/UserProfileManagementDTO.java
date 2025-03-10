package bg.project.SidikaFarm.web.dto;

public class UserProfileManagementDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public UserProfileManagementDTO() {
    }

    public UserProfileManagementDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileManagementDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileManagementDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileManagementDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public UserProfileManagementDTO setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserProfileManagementDTO setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserProfileManagementDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
