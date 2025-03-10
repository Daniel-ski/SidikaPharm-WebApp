package bg.project.SidikaFarm.web.dto;

import bg.project.SidikaFarm.model.entity.enums.DeliveryType;
import bg.project.SidikaFarm.model.entity.enums.PaymentMethod;

public class DeliveryDetailsDTO {
    private String firstName;
    private String lastName;
    private String town;
    private String postCode;
    private String street;
    private int number;
    private int floor;
    private int apartment;
    private String note;
    private DeliveryType deliveryType;
    private PaymentMethod paymentMethod;
    private String officeAddress;

    public DeliveryDetailsDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public DeliveryDetailsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public DeliveryDetailsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getTown() {
        return town;
    }

    public DeliveryDetailsDTO setTown(String town) {
        this.town = town;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public DeliveryDetailsDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public DeliveryDetailsDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public DeliveryDetailsDTO setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public DeliveryDetailsDTO setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public int getApartment() {
        return apartment;
    }

    public DeliveryDetailsDTO setApartment(int apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getNote() {
        return note;
    }

    public DeliveryDetailsDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public DeliveryDetailsDTO setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public DeliveryDetailsDTO setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public DeliveryDetailsDTO setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
}
