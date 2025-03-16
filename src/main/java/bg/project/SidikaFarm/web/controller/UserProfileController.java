package bg.project.SidikaFarm.web.controller;

import bg.project.SidikaFarm.service.interfaces.OrderService;
import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/user-profile")
public class UserProfileController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserProfileController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @ModelAttribute("baseUserInfoDTO")
    public BaseUserInfoDTO getBaseUserInfoDTO(@AuthenticationPrincipal UserDetails userDetails){
        return userService.getBaseUserInfo(userDetails.getUsername());
    }

    @GetMapping("/management")
    public String userProfileManagementPage(Authentication authentication,Model model){

        if (!model.containsAttribute("userProfileManagementDTO")){
            UserProfileManagementDTO userProfileManagementDTO = this.userService.getUserProfileManagementDTO(authentication.getName());

            model.addAttribute("userProfileManagementDTO", userProfileManagementDTO);
            model.addAttribute("userProfilePasswordUpdateDTO",new UserProfilePasswordUpdateDTO());
        }
        return "user/profile/management";
    }

    @PatchMapping("/management/update")
    public String profileManagementUpdate(
                                          @Valid UserProfileManagementDTO userProfileManagementDTO,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes,
                                          Authentication authentication
                                          ){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userProfileManagementDTO", userProfileManagementDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileManagementDTO",bindingResult);

            return "redirect:/user-profile/management";
        }
        userService.profileManagementUpdate(authentication.getName(), userProfileManagementDTO);

        return "redirect:/user-profile/management?success";
    }

    @PatchMapping("/management/update-password")
    public String userProfilePasswordUpdate(@Valid UserProfilePasswordUpdateDTO userProfilePasswordUpdateDTO,
                                            Authentication authentication,
                                            BindingResult bindingResult,
                                            RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userProfilePasswordUpdateDTO",userProfilePasswordUpdateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfilePasswordUpdateDTO",bindingResult);

            return "redirect:/user-profile/management";
        }

        boolean isUpdate = userService.profilePasswordUpdate(authentication.getName(), userProfilePasswordUpdateDTO);

        if (!isUpdate){
            return "redirect:/user-profile/management";
        }

        return "redirect:/user-profile/management?successchange";
    }

    @GetMapping("/favoriteProducts")
    public String userProfileFavoriteProductsPage(@AuthenticationPrincipal UserDetails userDetails,Model model){
        UserProfileDTO userProfileDTO = userService.getUserProfile(userDetails.getUsername());
        model.addAttribute("userProfileDTO",userProfileDTO);
        model.addAttribute("favoriteProducts",userProfileDTO.getFavoriteProducts());
        return "user/profile/favorite-products";
    }

    @GetMapping("/orders")
    public String userProfileOrdersPage(Authentication authentication,Model model){


        Set<OrderInfoDTO> allOrders = orderService.getOrdersByUserEmail(authentication.getName());

        model.addAttribute("allOrders",allOrders);
        return "user/profile/orders";
    }

    @GetMapping("/shipping")
    public String userProfileShippingPage(@AuthenticationPrincipal UserDetails userDetails,Model model){

        if (!model.containsAttribute("userProfileDTO")) {
            UserProfileDTO userProfileDTO = userService.getUserProfile(userDetails.getUsername());

            if (userProfileDTO.getDeliveryDetails() == null){
                userProfileDTO.setDeliveryDetails(new DeliveryDetailsDTO());
            }
            model.addAttribute("userProfileDTO",userProfileDTO);
        }


        return "user/profile/shipping";
    }

    @PatchMapping("/shipping/update")
    public String userProfileShippingUpdate(@ModelAttribute("userProfileDTO")
                                            @Valid UserProfileDTO userProfileDTO,
                                            BindingResult bindingResult,
                                            RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userProfileDTO",userProfileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileDTO",bindingResult);

            return "redirect:/user-profile/shipping";
        }
        boolean isSuccessUpdate = userService.userProfileShippingUpdate(userProfileDTO);

        if (!isSuccessUpdate){
            return "redirect:/user-profile/shipping";
        }

        return "redirect:/user-profile/shipping?success";
    }

    @GetMapping("/email-preferences")
    public String userProfileEmailPreferencesPage(@AuthenticationPrincipal UserDetails userDetails,Model model){
        UserProfileDTO userProfileDTO = userService.getUserProfile(userDetails.getUsername());
        model.addAttribute("userProfileDTO",userProfileDTO);
        return "user/profile/email-preferences";
    }



//    @GetMapping("/{section}")
//    public String loadSection(@PathVariable String section,
//                              @AuthenticationPrincipal UserDetails userDetails,
//                              Model model){
//
//        UserProfileDTO userProfileDTO = userService.getUserProfile(userDetails.getUsername());
//
//        model.addAttribute("userProfileDTO",userProfileDTO);
//        model.addAttribute("activeTab",section);
//
//        return "user/user-profile";
//    }
}
