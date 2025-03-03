package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.repository.ProductRepository;
import bg.project.SidikaFarm.service.interfaces.ProductService;
import bg.project.SidikaFarm.web.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO getProductDTOById(Long id) {
        Product productById = this.productRepository
                .getProductById(id).orElseThrow(NoSuchElementException::new);

        return this.mapper.map(productById,ProductDTO.class);

    }

    @Override
    public Product getProductById(Long id) {
        return this.productRepository
                .getProductById(id).orElseThrow(NoSuchElementException::new);
    }
}
