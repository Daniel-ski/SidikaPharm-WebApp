package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.web.dto.ProductDTO;
import org.springframework.stereotype.Service;


public interface ProductService {
    ProductDTO getProductDTOById(Long id);
    Product getProductById(Long id);
}
