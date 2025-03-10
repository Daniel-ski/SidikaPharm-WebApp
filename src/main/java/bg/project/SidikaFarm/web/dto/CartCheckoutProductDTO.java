package bg.project.SidikaFarm.web.dto;

import java.math.BigDecimal;

public class CartCheckoutProductDTO {
    private String name;
    private BigDecimal price;
    private int quantity;
    private String image;

    public CartCheckoutProductDTO() {
    }

    public String getName() {
        return name;
    }

    public CartCheckoutProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CartCheckoutProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartCheckoutProductDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getImage() {
        return image;
    }

    public CartCheckoutProductDTO setImage(String image) {
        this.image = image;
        return this;
    }
}
