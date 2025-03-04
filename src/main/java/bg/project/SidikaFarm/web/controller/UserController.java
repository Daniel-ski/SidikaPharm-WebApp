package bg.project.SidikaFarm.web.controller;

import bg.project.SidikaFarm.service.interfaces.CartService;
import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final CartService cartService;

    @Autowired
    public UserController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @ModelAttribute("baseUserInfoDTO")
        public BaseUserInfoDTO getBaseUserInfoDTO(Authentication authentication){
        return this.userService.getBaseUserInfo(authentication.getName());
    }


    @GetMapping("/login")
    public String loginPage(){

        return "login";
    }


    @GetMapping("/register")
    public String registerPage(Model model){

        if (!model.containsAttribute("createRegisterDTO")){
            model.addAttribute("createRegisterDTO" ,new CreateRegisterDTO());
        }
        return "registration";
    }

    @PostMapping("/register")
    public String doRegister(@Valid CreateRegisterDTO createRegisterDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createRegisterDTO",createRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createRegisterDTO",bindingResult);

            return "redirect:/registration";
        }

        userService.register(createRegisterDTO);

        return "redirect:/login";
    }

    @GetMapping("/user/cart")
    public String userCartPage(Authentication authentication,
                               Model model){

        BaseUserInfoDTO baseUserInfoDTO = userService.getBaseUserInfo(authentication.getName());
        UserCartDTO userCartDTO = cartService.userCart(authentication.getName());

        model.addAttribute("baseUserInfoDTO",baseUserInfoDTO);
        model.addAttribute("userCartDTO",userCartDTO);
        return "cart";
    }

    @PostMapping("/user/cart")
    public String cartCheckout(@RequestParam("cartData") String cartDataJSON,
                               @ModelAttribute("createOrderDTO")@Valid CreateOrderDTO createOrderDTO,
                               Authentication authentication,
                               Model model,
                               RedirectAttributes redirectAttributes){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<Long, CartCheckoutProductDTO> cartData = objectMapper.readValue(cartDataJSON, new TypeReference<>() {});


            DeliveryDetailsDTO userDeliveryDetails = userService.getUserDeliveryDetails(authentication.getName());

            if (userDeliveryDetails != null){
                createOrderDTO.setDeliveryDetailsDTO(userDeliveryDetails);
            }
            createOrderDTO.setCartProductData(cartData);
            redirectAttributes.addFlashAttribute("createOrderDTO",createOrderDTO);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/user/cart";
        }
        return "redirect:/user/cart-checkout";
    }

    @DeleteMapping("/user/cart/product-remove/{id}")
    public String cartProductDelete(@PathVariable Long id,Authentication authentication){
        cartService.cartProductDelete(id,authentication.getName());

        return "redirect:/user/cart";
    }

    @GetMapping("/user/cart-checkout")
    public String cartCheckoutPage(@ModelAttribute("createOrderDTO")CreateOrderDTO createOrderDTO,
                                                                    Model model){

        if (!model.containsAttribute("createOrderDTO")){
            model.addAttribute("createOrderDTO",new CreateOrderDTO());
        }
        return "cart-checkout";
    }

//    @GetMapping("user/order/complete")
//    public String orderCompletePage(){
//        return "order-complete";
//    }

    @PostMapping("user/cart-checkout")
    public String orderComplete(@ModelAttribute("createOrderDTO") CreateOrderDTO createOrderDTO,
                                Model model,
                                Authentication authentication,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("createOrderDTO",createOrderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOrderDTO",bindingResult);
            return "redirect:/user/cart-checkout";
        }

        userService.createNewOrder(createOrderDTO,authentication.getName());

        return "redirect:/user/order/complete";
    }

    @GetMapping("user/order/complete")
    public String orderCompletePage(){
        return "order-complete";
    }

}
