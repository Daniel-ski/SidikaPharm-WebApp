package bg.project.SidikaFarm.web.dto;

import bg.project.SidikaFarm.model.entity.Image;
import bg.project.SidikaFarm.model.entity.Promotion;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;
    private String title;
    private String brand;
    private BigDecimal price;
    private int rating;
    private String mainDescription;
    private String usageInstructions;
    private Image image;
    private Promotion promotion;
    private int quantity;
    private int availability;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, BigDecimal price, Image image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public ProductDTO setPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ProductDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public ProductDTO setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public ProductDTO setMainDescription(String mainDescription) {
        this.mainDescription = mainDescription;
        return this;
    }

    public String getUsageDescription() {
        return usageInstructions;
    }

    public ProductDTO setUsageInstructions(String usageInstructions) {
        this.usageInstructions = usageInstructions;
        return this;
    }

    public ProductDTO setImage(Image image) {
        this.image = image;
        return this;
    }

    public int getAvailability() {
        return availability;
    }

    public ProductDTO setAvailability(int availability) {
        this.availability = availability;
        return this;
    }
}
