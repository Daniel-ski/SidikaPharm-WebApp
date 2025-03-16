package bg.project.SidikaFarm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table
public class BaseProductInfo extends BaseEntity {
    private Long productId;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String image;
    @ManyToOne
    private Promotion promotion;

    public BaseProductInfo() {
    }

    public BaseProductInfo(Long productId, String name, BigDecimal price, int quantity, String image) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public Long getProductId() {
        return productId;
    }

    public BaseProductInfo setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BaseProductInfo setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BaseProductInfo setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public BaseProductInfo setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getImage() {
        return image;
    }

    public BaseProductInfo setImage(String image) {
        this.image = image;
        return this;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public BaseProductInfo setPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }
}
