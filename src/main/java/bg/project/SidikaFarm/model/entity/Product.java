package bg.project.SidikaFarm.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String title;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private BigDecimal price;
    private int rating;

    @Column(name = "main_description", nullable = false, columnDefinition = "TEXT")
    private String mainDescription;
    @Column(name = "usage_instruction", nullable = false)
    private String usageInstructions;
    @OneToOne(optional = false)
    private Image image;

    @ManyToOne
    private Promotion promotion;

    @ManyToMany()
    private Set<Category> categories;
    @Column(nullable = false)
    private int availability;


    public Product() {
        this.categories = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public void setMainDescription(String mainDescription) {
        this.mainDescription = mainDescription;
    }

    public String getUsageInstructions() {
        return usageInstructions;
    }

    public void setUsageInstructions(String usageInstructions) {
        this.usageInstructions = usageInstructions;
    }

    public Image getPicture() {
        return image;
    }

    public void setPicture(Image image) {
        this.image = image;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public Image getImage() {
        return image;
    }

    public Product setImage(Image image) {
        this.image = image;
        return this;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Product setPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }
}
