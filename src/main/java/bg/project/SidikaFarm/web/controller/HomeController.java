package bg.project.SidikaFarm.web.controller;

import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.BaseUserInfoDTO;
import bg.project.SidikaFarm.web.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("baseUserInfoDTO")
    public BaseUserInfoDTO getBaseUserInfo(Authentication authentication){
        return userService.getBaseUserInfo(authentication.getName());
    }

//    @ModelAttribute("userProfileDTO")
//    public UserProfileDTO getUserProfileDTO(@AuthenticationPrincipal UserDetails userDetails){
//        return userService.getUserProfile(userDetails.getUsername());
//    }

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal UserDetails userDetails,Model model){

        if (userDetails != null){
            UserProfileDTO userProfileDTO = userService.getUserProfile(userDetails.getUsername());
            model.addAttribute("userProfileDTO",userProfileDTO);
        }else {
            model.addAttribute("userProfileDTO",new UserProfileDTO());
        }

        return "index";
    }
}
