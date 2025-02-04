package bg.project.SidikaFarm.model.entity.enums;

public enum RoleType {
    ADMIN("admin"),
    USER("user"),
    PHARMACIST("pharmacist");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
