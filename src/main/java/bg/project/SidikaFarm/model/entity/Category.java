package bg.project.SidikaFarm.model.entity;

import bg.project.SidikaFarm.model.entity.enums.CategoryName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(name = "category_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @ManyToMany(targetEntity = Product.class, mappedBy = "categories")
    private List<Product> products;

    public Category() {
        this.products = new ArrayList<>();
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
