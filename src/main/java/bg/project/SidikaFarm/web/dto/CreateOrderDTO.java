package bg.project.SidikaFarm.web.dto;

import java.util.Map;

public class CreateOrderDTO {
    private DeliveryDetailsDTO deliveryDetailsDTO;
    private Map<Long,CartCheckoutProductDTO> cartProductData;

    public CreateOrderDTO() {
    }

    public DeliveryDetailsDTO getDeliveryDetailsDTO() {
        return deliveryDetailsDTO;
    }

    public CreateOrderDTO setDeliveryDetailsDTO(DeliveryDetailsDTO deliveryDetailsDTO) {
        this.deliveryDetailsDTO = deliveryDetailsDTO;
        return this;
    }

    public Map<Long, CartCheckoutProductDTO> getCartProductData() {
        return cartProductData;
    }

    public CreateOrderDTO setCartProductData(Map<Long, CartCheckoutProductDTO> cartProductData) {
        this.cartProductData = cartProductData;
        return this;
    }
}
