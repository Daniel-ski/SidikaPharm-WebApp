package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.model.entity.BaseProductInfo;
import bg.project.SidikaFarm.model.entity.Cart;
import bg.project.SidikaFarm.model.entity.Product;
import bg.project.SidikaFarm.model.entity.User;
import bg.project.SidikaFarm.repository.BaseProductInfoRepository;
import bg.project.SidikaFarm.repository.CartRepository;
import bg.project.SidikaFarm.repository.UserRepository;
import bg.project.SidikaFarm.service.interfaces.CartService;
import bg.project.SidikaFarm.service.interfaces.ProductService;
import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.BaseProductInfoDTO;
import bg.project.SidikaFarm.web.dto.ProductDTO;
import bg.project.SidikaFarm.web.dto.UserCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductService productService;
    private final BaseProductInfoRepository baseProductInfoRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, UserService userService, ProductService productService, BaseProductInfoRepository baseProductInfoRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.productService = productService;
        this.baseProductInfoRepository = baseProductInfoRepository;
    }

    @Override
    public UserCartDTO userCart(String email) {
        User user = this.userRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        Cart cart = user.getCart();

        UserCartDTO userCartDTO = new UserCartDTO();

        return mapToCartDTO(cart);

    }

    @Override
    public void cartProductDelete(Long id, String email) {
        User user = this.userRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        Cart cart = user.getCart();

        cart.getProducts().removeIf(product -> product.getId() == id);

        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Long id,int quantity) {

        User currentUser = this.userService.getCurrentUser();
        Cart cart = this.cartRepository.findCartById(currentUser.getCart().getId());
        Product productToAdd = this.productService.getProductById(id);
        BaseProductInfo baseProductInfo = mapProductToBaseProductInfo(productToAdd, quantity);

        cart.setProduct(baseProductInfo);
        this.cartRepository.save(cart);
    }

    private BaseProductInfo mapProductToBaseProductInfo(Product productToAdd,int quantity) {
        BaseProductInfo baseProductInfo = this.baseProductInfoRepository.findBaseProductInfoById(productToAdd.getId()).orElseGet(BaseProductInfo::new);

        if (baseProductInfo.getProductId() == null){
            baseProductInfo.setProductId(productToAdd.getId())
                    .setName(productToAdd.getTitle())
                    .setPrice(productToAdd.getPrice())
                    .setImage(productToAdd.getImage().getLocatedOn())
                    .setQuantity(quantity)
                    .setPromotion(productToAdd.getPromotion());
        }

        baseProductInfo.setQuantity(baseProductInfo.getQuantity() + quantity);

        return baseProductInfo;


    }

    private UserCartDTO mapToCartDTO(Cart cart){
        UserCartDTO userCartDTO = new UserCartDTO();
        UserCartDTO.setBalanceToFreeShipping(49.99);

        userCartDTO.setBaseProductsInfoDTO(cart.getProducts()
                                        .stream()
                                        .map(product ->
                                            new BaseProductInfoDTO()
                                                    .setProductId(product.getProductId())
                                                    .setName(product.getName())
                                                    .setImage(product.getImage())
                                                    .setPrice(product.getPrice())
                                                    .setQuantity(product.getQuantity())
                                        )
                                .collect(Collectors.toSet()))
                                .setId(cart.getId());



      return calculateTotalPrice(userCartDTO.getProductsDTO(),userCartDTO);

    }

    private UserCartDTO calculateTotalPrice(Set<BaseProductInfoDTO> productsDTO ,UserCartDTO userCartDTO){
        BigDecimal[] result = productsDTO
                .stream()
                .map(product -> {
                    BigDecimal price = product.getPrice();
                    BigDecimal savedMoney = BigDecimal.ZERO;

                    if (product.getPromotion() != null) {
                        Double discountPercentage = product.getPromotion().getDiscountPercentage();

                        price = product.getPrice().multiply(BigDecimal.valueOf(1-discountPercentage / 100))
                                .setScale(2,RoundingMode.HALF_UP);

                        savedMoney = product.getPrice().subtract(price);

                    }
                    return new  BigDecimal[]{price,savedMoney};
                })
                .reduce(new BigDecimal[]{BigDecimal.ZERO,BigDecimal.ZERO},(acc,values) -> {
                    acc[0] = acc[0].add(values[0]);
                    acc[1] = acc[1].add(values[1]);

                    return acc;
                });

        BigDecimal totalPrice = result[0].setScale(2,RoundingMode.HALF_UP);
        BigDecimal savedMoney = result[1].setScale(2,RoundingMode.HALF_UP);

        userCartDTO.setTotalPrice(totalPrice)
            .setSavedMoney(savedMoney);

        return userCartDTO;
    }
}
