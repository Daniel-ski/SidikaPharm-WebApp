package bg.project.SidikaFarm.model.entity.enums;

public enum OrderStatus {
    COMPLETE("Изпълнена"),
    IN_PROGRESS("Изпълнява се"),
    REFUSED("Отказана");

    private String value;

    OrderStatus (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
