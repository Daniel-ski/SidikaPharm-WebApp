package bg.project.SidikaFarm.repository;

import bg.project.SidikaFarm.model.entity.Cart;
import bg.project.SidikaFarm.model.entity.DeliveryDetails;
import bg.project.SidikaFarm.model.entity.User;
import bg.project.SidikaFarm.web.dto.BaseUserInfoDTO;
import bg.project.SidikaFarm.web.dto.UserProfileManagementDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    @Query("""
        SELECT u.cart
        FROM User u
        WHERE u.email = :email
""")
    Optional<Cart> findCartByUserEmail(@Param("email") String email);
    @Query("SELECT u.deliveryDetails FROM User u WHERE u.email = :email")
    Optional<DeliveryDetails> findDeliveryDetailsByUserEmail(@Param("email") String email);
    @Query("""
        SELECT new bg.project.SidikaFarm.web.dto.BaseUserInfoDTO(u.firstName,u.lastName,SIZE(u.favoriteProducts),SIZE(u.cart.products))
        FROM User u
        WHERE u.email = :email
    """)
    Optional<BaseUserInfoDTO> getBaseUserInfo(@Param("email") String email);

    @Query(""" 
    SELECT new bg.project.SidikaFarm.web.dto.UserProfileManagementDTO(u.firstName,u.lastName,u.email)
    FROM User u
    WHERE u.email = :email
""")
    Optional<UserProfileManagementDTO> getUserProfileManagementDTO(@Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.deliveryDetails = :deliveryDetails WHERE u.email = :email")
    void updateDeliveryDetailsByEmail(@Param("email") String email, @Param("deliveryDetails") DeliveryDetails deliveryDetails);

}
