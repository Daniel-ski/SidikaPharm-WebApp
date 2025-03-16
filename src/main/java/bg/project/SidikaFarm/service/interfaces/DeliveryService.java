package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.model.entity.DeliveryDetails;
import bg.project.SidikaFarm.web.dto.DeliveryDetailsDTO;

public interface DeliveryService {
    DeliveryDetailsDTO mapToDeliveryDetailsDTO(DeliveryDetails deliveryDetails);
    DeliveryDetails mapDeliveryDetailsDTOToEntity(DeliveryDetailsDTO deliveryDetailsDTO,DeliveryDetails deliveryDetails);
    DeliveryDetails saveDeliveryDetails(DeliveryDetails deliveryDetails);
}
