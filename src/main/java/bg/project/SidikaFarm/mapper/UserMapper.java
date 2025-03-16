package bg.project.SidikaFarm.mapper;

import bg.project.SidikaFarm.model.entity.Cart;
import bg.project.SidikaFarm.model.entity.User;
import bg.project.SidikaFarm.web.dto.CreateRegisterDTO;
import bg.project.SidikaFarm.web.dto.UserProfileDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserMapper{

    public User mapFromCreateRegisterDTO(CreateRegisterDTO createRegisterDTO){
        User user = new User();

        return   user
                .setFirstName(createRegisterDTO.getFirstName())
                .setLastName(createRegisterDTO.getLastName())
                .setCity(createRegisterDTO.getCity())
                .setEmail(createRegisterDTO.getEmail())
                .setRegisteredOn(createRegisterDTO.getRegisteredOn())
                .setCart(new Cart())
                .setFavoriteProducts(new HashSet<>())
                .setArticles(new HashSet<>())
                .setRoles(new HashSet<>());
    }

    public UserProfileDTO mapToUserProfileDTO(User user){
        UserProfileDTO userProfileDTO = new UserProfileDTO();

      return userProfileDTO
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setCity(user.getCity())
                .setEmail(user.getEmail())
                .setRegisteredOn(user.getRegisteredOn())
                .setCart(user.getCart())
                .setFavoriteProducts(user.getFavoriteProducts())
                .setArticles(user.getArticles())
                .setRoles(user.getRoles());
    }

}
