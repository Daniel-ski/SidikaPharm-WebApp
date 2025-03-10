package bg.project.SidikaFarm.web.dto;

import bg.project.SidikaFarm.model.entity.Promotion;

import java.math.BigDecimal;

public class BaseProductInfoDTO {
    private Long productId;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String image;
    private Promotion promotion;

    public BaseProductInfoDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public BaseProductInfoDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BaseProductInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BaseProductInfoDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public BaseProductInfoDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getImage() {
        return image;
    }

    public BaseProductInfoDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public BaseProductInfoDTO setPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }
}
