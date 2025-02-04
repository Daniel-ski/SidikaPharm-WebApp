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

    public User() {

    }

    private User (UserBuilder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.city = builder.city;
        this.email = builder.email;
        this.password = builder.password;
        this.registeredOn = builder.registeredOn;
        this.cart = builder.cart;
        this.favoriteProducts = builder.favoriteProducts;
        this.articles = builder.articles;
        this.roles = builder.roles;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static User mapFromDTO(CreateRegisterDTO createRegisterDTO, PasswordEncoder passwordEncoder){
        return User.builder()
                .setFirstName(createRegisterDTO.getFirstName())
                .setLastName(createRegisterDTO.getLastName())
                .setCity(createRegisterDTO.getCity())
                .setEmail(createRegisterDTO.getEmail())
                .setPassword(passwordEncoder.encode(createRegisterDTO.getPassword()))
                .setRegisteredOn(createRegisterDTO.getRegisteredOn())
                .setCart(new Cart())
                .setFavoriteProducts(new HashSet<>())
                .setArticles(new HashSet<>())
                .setRoles(new HashSet<>())
                .build();

    }

    public static class UserBuilder {

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

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRegisteredOn(LocalDateTime registeredOn) {
            this.registeredOn = registeredOn;
            return this;
        }

        public UserBuilder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public UserBuilder setFavoriteProducts(Set<Product> favoriteProducts) {
            this.favoriteProducts = favoriteProducts;
            return this;
        }

        public UserBuilder setArticles(Set<Article> articles) {
            this.articles = articles;
            return this;
        }

        public UserBuilder setRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(Set<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }
}
