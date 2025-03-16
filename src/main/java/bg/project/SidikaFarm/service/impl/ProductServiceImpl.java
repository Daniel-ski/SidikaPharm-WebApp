package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.mapper.ProductMapper;
import bg.project.SidikaFarm.model.entity.BaseProductInfo;
import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.repository.BaseProductInfoRepository;
import bg.project.SidikaFarm.repository.ProductRepository;
import bg.project.SidikaFarm.service.interfaces.ProductService;
import bg.project.SidikaFarm.web.dto.CartCheckoutProductDTO;
import bg.project.SidikaFarm.web.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BaseProductInfoRepository baseProductInfoRepository;
    private final ProductMapper productMapper;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, BaseProductInfoRepository baseProductInfoRepository, ProductMapper productMapper, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.baseProductInfoRepository = baseProductInfoRepository;
        this.productMapper = productMapper;
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

    @Override
    public Set<BaseProductInfo> mapToBaseProductInfoSet(Map<Long, CartCheckoutProductDTO> cartProductData) {
        Set<BaseProductInfo> productInfoSet = this.productMapper.mapToBaseProductInfoSet(cartProductData);

        this.baseProductInfoRepository.saveAll(productInfoSet);

        return productInfoSet;
    }
}
