package bg.project.SidikaFarm.model.entity.enums;

public enum DeliveryType {
    TO_ADDRESS("До Адрес"),
    TO_OFFICE("До Офис");

    private String value;

    DeliveryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
