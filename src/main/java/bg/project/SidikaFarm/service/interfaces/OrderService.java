package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.model.entity.Order;
import bg.project.SidikaFarm.web.dto.CreateOrderDTO;
import bg.project.SidikaFarm.web.dto.OrderInfoDTO;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<OrderInfoDTO> getOrdersByUserEmail(String email);
    void createNewOrder(CreateOrderDTO createOrderDTO);
}
