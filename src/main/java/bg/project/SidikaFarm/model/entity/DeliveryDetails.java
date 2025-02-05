package bg.project.SidikaFarm.model.entity;

import bg.project.SidikaFarm.model.entity.enums.DeliveryType;
import jakarta.persistence.*;

@Entity
@Table(name = "delivery_details")
public class DeliveryDetails extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String town;
    @Column(name = "post_code")
    private String postCode;
    private String street;
    private int number;
    private int floor;
    private int apartment;
    private String note;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    public DeliveryDetails() {
    }

    public String getFirstName() {
        return firstName;
    }

    public DeliveryDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public DeliveryDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getTown() {
        return town;
    }

    public DeliveryDetails setTown(String town) {
        this.town = town;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public DeliveryDetails setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public DeliveryDetails setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public DeliveryDetails setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public DeliveryDetails setFloor(int floor) {
        this.floor = floor;
        return this;
    }

    public int getApartment() {
        return apartment;
    }

    public DeliveryDetails setApartment(int apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getNote() {
        return note;
    }

    public DeliveryDetails setNote(String note) {
        this.note = note;
        return this;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public DeliveryDetails setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }
}
