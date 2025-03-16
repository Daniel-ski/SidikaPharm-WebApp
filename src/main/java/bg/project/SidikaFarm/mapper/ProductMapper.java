package bg.project.SidikaFarm.mapper;

import bg.project.SidikaFarm.model.entity.BaseProductInfo;
import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.web.dto.BaseProductInfoDTO;
import bg.project.SidikaFarm.web.dto.CartCheckoutProductDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ProductMapper {

    public Set<BaseProductInfo> mapToBaseProductInfoSet(Map<Long, CartCheckoutProductDTO> cartProductData){
        Set<BaseProductInfo> productInfoSet = new HashSet<>();

        cartProductData
                .forEach((productId,dto) -> {

                    productInfoSet.add(new BaseProductInfo()
                            .setProductId(productId)
                            .setName(dto.getName())
                            .setPrice(dto.getPrice())
                            .setQuantity(dto.getQuantity())
                            .setImage(dto.getImage()));
                });

        return productInfoSet;
    }
}
