package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.mapper.OrderMapper;
import bg.project.SidikaFarm.model.entity.BaseProductInfo;
import bg.project.SidikaFarm.model.entity.DeliveryDetails;
import bg.project.SidikaFarm.model.entity.Order;
import bg.project.SidikaFarm.model.entity.enums.OrderStatus;
import bg.project.SidikaFarm.repository.OrderRepository;
import bg.project.SidikaFarm.service.interfaces.DeliveryService;
import bg.project.SidikaFarm.service.interfaces.OrderService;
import bg.project.SidikaFarm.service.interfaces.ProductService;
import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.CartCheckoutProductDTO;
import bg.project.SidikaFarm.web.dto.CreateOrderDTO;
import bg.project.SidikaFarm.web.dto.OrderInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final DeliveryService deliveryService;
    private final OrderMapper orderMapper;

    private final ModelMapper mapper;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductService productService, DeliveryService deliveryService, OrderMapper orderMapper, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.deliveryService = deliveryService;
        this.orderMapper = orderMapper;
        this.mapper = mapper;
    }

    @Override
    public Set<OrderInfoDTO> getOrdersByUserEmail(String email) {
        Set<Order> orders = this.orderRepository.findAllByOwner_Email(email).orElseThrow(() -> new RuntimeException("Orders not found"));

        return mapToOrderInfoDTO(orders);

    }

    @Override
    public void createNewOrder(CreateOrderDTO createOrderDTO) {
        Order order = new Order();

        order.setOwner(this.userService.getCurrentUser());

        Set<BaseProductInfo> productInfoSet = this.productService.mapToBaseProductInfoSet(createOrderDTO.getCartProductData());
        order.setProducts(productInfoSet);

        order
                .setDeliveryDetails(
                        this.deliveryService
                        .mapDeliveryDetailsDTOToEntity(createOrderDTO.getDeliveryDetailsDTO(),new DeliveryDetails()));

        order.setTotalPrice(calculateTotalPrice(createOrderDTO.getCartProductData()));
        order.setCreatedOn(LocalDateTime.now());
        order.setStatus(OrderStatus.IN_PROGRESS);


        this.orderRepository.save(order);
    }

    private Set<OrderInfoDTO> mapToOrderInfoDTO(Set<Order> orders) {
       Set<OrderInfoDTO> orderInfoDTO = new HashSet<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        orders
                .forEach(order -> {

                orderInfoDTO.add(new OrderInfoDTO()
                        .setId(order.getId())
                        .setStatus(order.getStatus())
                        .setTotalPrice(order.getTotalPrice())
                        .setCreatedOn(order.getCreatedOn().format(formatter))
                        .setProducts(mapToProductsDTO(orders))
                        .setDeliveryType(order.getDeliveryDetails().getDeliveryType())
                );
                });

        return orderInfoDTO;
    }

    private Set<CartCheckoutProductDTO> mapToProductsDTO(Set<Order> orders) {
        Set<CartCheckoutProductDTO> baseProductsInfo = new HashSet<>();

        orders.forEach(order -> {

            order
                    .getProducts()
                    .forEach(product -> {
                        baseProductsInfo.add(mapper.map(product, CartCheckoutProductDTO.class));
                    });

        });

        return baseProductsInfo;
    }

    private BigDecimal calculateTotalPrice(Map<Long, CartCheckoutProductDTO> cartProductData) {
        BigDecimal totalPrice = new BigDecimal(0);

        return cartProductData
                .values()
                .stream()
                .map(dto -> dto.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);

    }
}
