package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.model.entity.User;
import bg.project.SidikaFarm.web.dto.*;

public interface UserService {
   void register(CreateRegisterDTO createRegisterDTO);
   UserProfileDTO getUserProfile(String email);

    void profileManagementUpdate(String email,UserProfileManagementDTO userProfileManagementDTO);

    boolean profilePasswordUpdate(String email, UserProfilePasswordUpdateDTO userProfilePasswordUpdateDTO);

    BaseUserInfoDTO getBaseUserInfo(String email);

    DeliveryDetailsDTO getUserDeliveryDetails(String email);

    boolean userProfileShippingUpdate(UserProfileDTO userProfileDTO);


    UserProfileManagementDTO getUserProfileManagementDTO(String email);

    User getCurrentUser();
    String getCurrentUserEmail();


    void addProductToFavorite(Long id);
}
