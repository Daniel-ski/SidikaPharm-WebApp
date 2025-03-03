package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.model.entity.Order;
import bg.project.SidikaFarm.repository.OrderRepository;
import bg.project.SidikaFarm.service.interfaces.OrderService;
import bg.project.SidikaFarm.service.interfaces.UserService;
import bg.project.SidikaFarm.web.dto.CartCheckoutProductDTO;
import bg.project.SidikaFarm.web.dto.OrderInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelMapper mapper;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public Set<OrderInfoDTO> getOrdersByUserEmail(String email) {
        Set<Order> orders = this.orderRepository.findAllByOwner_Email(email).orElseThrow(() -> new RuntimeException("Orders not found"));

        return mapToOrderInfoDTO(orders);

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
}
