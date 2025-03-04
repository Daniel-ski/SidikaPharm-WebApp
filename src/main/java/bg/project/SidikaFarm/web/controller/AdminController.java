package bg.project.SidikaFarm.web.controller;

import bg.project.SidikaFarm.web.dto.CreateArticleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {



    @GetMapping()
    public String mainAdminPanel(){
        return "admin/admin-panel";
    }

    @GetMapping("/order-graph")
    public String orderGraphPage(){
        return "admin/order-graph";
    }


    @GetMapping("/users")
    public String usersPage(){
        return "admin/users";
    }

    @GetMapping("/orders")
    public String ordersPage(){
        return "admin/orders";
    }

    @GetMapping("/products")
    public String productsPage(){
        return "admin/products";
    }

    @GetMapping("/products/create-product")
    public String createProductPage(){
        return "admin/new-product";
    }

    @GetMapping("/categories")
    public String categoriesPage(){
        return "admin/categories";
    }
}
