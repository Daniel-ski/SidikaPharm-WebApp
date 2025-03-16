package bg.project.SidikaFarm.model.entity;

import bg.project.SidikaFarm.model.entity.enums.RoleType;
import bg.project.SidikaFarm.web.dto.CreateRegisterDTO;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "registered_on", nullable = false)
    private LocalDateTime registeredOn;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToMany
    private Set<Product> favoriteProducts;

    @OneToMany(targetEntity = Article.class,mappedBy = "author")
    private Set<Article> articles;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToOne
    private DeliveryDetails deliveryDetails;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public User setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    public Cart getCart() {
        return cart;
    }

    public User setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public Set<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public User setFavoriteProducts(Set<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
        return this;
    }

    public void setFavoriteProduct(Product product){
        this.favoriteProducts.add(product);
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public User setArticles(Set<Article> articles) {
        this.articles = articles;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public User setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
        return this;
    }
}
