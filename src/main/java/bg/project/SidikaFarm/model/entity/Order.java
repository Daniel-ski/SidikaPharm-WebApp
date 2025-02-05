package bg.project.SidikaFarm.model.entity;

import bg.project.SidikaFarm.model.entity.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_price")
    private BigDecimal totalPrice;


@ManyToOne(optional = false)
private User owner;
@ManyToMany()
private Set<Product> products;
    @ManyToOne(optional = false)
private DeliveryDetails deliveryDetails;

    public Order() {
        this.products = new HashSet<>();
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Order setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Order setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Order setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public Order setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
