package bg.project.SidikaFarm.web.dto;

import bg.project.SidikaFarm.model.entity.Article;
import bg.project.SidikaFarm.model.entity.Cart;
import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.model.entity.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserProfileDTO {
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String password;
    private LocalDateTime registeredOn;
    private Cart cart;
    private Set<Product> favoriteProducts;
    private Set<Article> articles;
    private Set<Role> roles;
    private DeliveryDetailsDTO deliveryDetails;

    public UserProfileDTO() {
        this.favoriteProducts = new HashSet<>();
        this.articles = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserProfileDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserProfileDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public UserProfileDTO setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    public Cart getCart() {
        return cart;
    }

    public UserProfileDTO setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public Set<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public UserProfileDTO setFavoriteProducts(Set<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
        return this;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public UserProfileDTO setArticles(Set<Article> articles) {
        this.articles = articles;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public UserProfileDTO setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public DeliveryDetailsDTO getDeliveryDetails() {
        return deliveryDetails;
    }

    public UserProfileDTO setDeliveryDetails(DeliveryDetailsDTO deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
        return this;
    }
}
