package bg.project.SidikaFarm.mapper;

import bg.project.SidikaFarm.model.entity.DeliveryDetails;
import bg.project.SidikaFarm.web.dto.DeliveryDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    public DeliveryDetailsDTO mapToDeliveryDetailsDTO(DeliveryDetails deliveryDetails){
        DeliveryDetailsDTO deliveryDetailsDTO = new DeliveryDetailsDTO();

        return deliveryDetailsDTO
                .setFirstName(deliveryDetails.getFirstName())
                .setLastName(deliveryDetails.getLastName())
                .setTown(deliveryDetails.getTown())
                .setPostCode(deliveryDetails.getPostCode())
                .setStreet(deliveryDetails.getStreet())
                .setNumber(deliveryDetails.getNumber())
                .setApartment(deliveryDetails.getApartment())
                .setFloor(deliveryDetails.getFloor())
                .setNote(deliveryDetails.getNote())
                .setDeliveryType(deliveryDetails.getDeliveryType())
                .setOfficeAddress(deliveryDetails.getOfficeAddress());
    }

    public DeliveryDetails mapDeliveryDetailsDTOToEntity(DeliveryDetailsDTO deliveryDetailsDTO,DeliveryDetails deliveryDetails) {
        return deliveryDetails
                .setFirstName(deliveryDetailsDTO.getFirstName())
                .setLastName(deliveryDetailsDTO.getLastName())
                .setTown(deliveryDetailsDTO.getTown())
                .setPostCode(deliveryDetailsDTO.getPostCode())
                .setStreet(deliveryDetailsDTO.getStreet())
                .setNumber(deliveryDetailsDTO.getNumber())
                .setFloor(deliveryDetailsDTO.getFloor())
                .setApartment(deliveryDetailsDTO.getApartment())
                .setOfficeAddress(deliveryDetailsDTO.getOfficeAddress())
                .setDeliveryType(deliveryDetailsDTO.getDeliveryType())
                .setNote(deliveryDetailsDTO.getNote())
                .setPaymentMethod(deliveryDetailsDTO.getPaymentMethod());
    }
}
