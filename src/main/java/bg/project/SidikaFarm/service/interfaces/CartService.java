package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.web.dto.UserCartDTO;

public interface CartService {
    UserCartDTO userCart(String email);

    void cartProductDelete(Long id, String email);

    void addProductToCart(Long id,int quantity);
}
