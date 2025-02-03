package bg.project.SidikaFarm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity{

    @Transient
    private static int BALANCE_TO_FREE_SHIPPING;

    private BigDecimal totalPrice;
    @ManyToMany
    private Set<Product> products;

    public Cart() {
        this.products = new HashSet<>();
    }

    public static int getBalanceToFreeShipping() {
        return BALANCE_TO_FREE_SHIPPING;
    }

    public static void setBalanceToFreeShipping(int balanceToFreeShipping) {
        BALANCE_TO_FREE_SHIPPING = balanceToFreeShipping;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
