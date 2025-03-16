package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.model.entity.BaseProductInfo;
import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.web.dto.CartCheckoutProductDTO;
import bg.project.SidikaFarm.web.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;


public interface ProductService {
    ProductDTO getProductDTOById(Long id);
    Product getProductById(Long id);

    Set<BaseProductInfo> mapToBaseProductInfoSet(Map<Long, CartCheckoutProductDTO> cartProductData);

}
