package bg.project.SidikaFarm.web.controller;

import bg.project.SidikaFarm.service.interfaces.CartService;
import bg.project.SidikaFarm.service.interfaces.ProductService;
import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.BaseUserInfoDTO;
import bg.project.SidikaFarm.web.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    public ProductController(ProductService productService, UserService userService, CartService cartService) {
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @ModelAttribute("baseUserInfoDTO")
    public BaseUserInfoDTO getBaseUserInfoDTO(Authentication authentication){
       return this.userService.getBaseUserInfo(authentication.getName());
    }

    @GetMapping("/product/details/{id}")
    public String getProductDetailsPage(@PathVariable Long id, Model model){
        ProductDTO productDTO = this.productService.getProductDTOById(id);

        model.addAttribute("productDTO",productDTO);

        return "product-details";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity,
                                       RedirectAttributes redirectAttributes,
                                       Model model){
        this.cartService.addProductToCart(productId,quantity);
    redirectAttributes.addAttribute("id",productId);

        return "redirect:/product/details/{id}";
    }


    @PostMapping("/favorites/add")
    public String addToFavorites(@RequestParam() Long productId,
                                 RedirectAttributes redirectAttributes){
        this.userService.addProductToFavorite(productId);
        redirectAttributes.addAttribute("id",productId);
        return "redirect:/product/details/{id}";
    }

//    @PostMapping("/favorites/add")
//    @ResponseBody
//    public ResponseEntity<String> addToFavorites(@RequestParam("id") Long productId){
//        this.userService.addProductToFavorite(productId);
//        return ResponseEntity.ok("Продуктът е добавен в любими.");
//    }
}
