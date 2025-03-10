package bg.project.SidikaFarm.web.dto;

import bg.project.SidikaFarm.model.entity.enums.DeliveryType;
import bg.project.SidikaFarm.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class OrderInfoDTO {
    private Long id;
    private String createdOn;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private Set<CartCheckoutProductDTO> products;
    private DeliveryType deliveryType;

    public OrderInfoDTO() {
    }

    public Long getId() {
        return id;
    }

    public OrderInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public OrderInfoDTO setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderInfoDTO setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderInfoDTO setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Set<CartCheckoutProductDTO> getProducts() {
        return products;
    }

    public OrderInfoDTO setProducts(Set<CartCheckoutProductDTO> products) {
        this.products = products;
        return this;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public OrderInfoDTO setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }
}
