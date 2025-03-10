package bg.project.SidikaFarm.web.dto;

public class UserProfilePasswordUpdateDTO {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public UserProfilePasswordUpdateDTO() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public UserProfilePasswordUpdateDTO setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserProfilePasswordUpdateDTO setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserProfilePasswordUpdateDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
