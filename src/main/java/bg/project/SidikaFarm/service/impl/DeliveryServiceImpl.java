package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.mapper.DeliveryMapper;
import bg.project.SidikaFarm.model.entity.DeliveryDetails;
import bg.project.SidikaFarm.repository.DeliveryDetailsRepository;
import bg.project.SidikaFarm.service.interfaces.DeliveryService;
import bg.project.SidikaFarm.web.dto.DeliveryDetailsDTO;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryDetailsRepository deliveryDetailsRepository;
    private final DeliveryMapper deliveryMapper;

    public DeliveryServiceImpl(DeliveryDetailsRepository deliveryDetailsRepository, DeliveryMapper deliveryMapper) {
        this.deliveryDetailsRepository = deliveryDetailsRepository;
        this.deliveryMapper = deliveryMapper;
    }

    @Override
    public DeliveryDetailsDTO mapToDeliveryDetailsDTO(DeliveryDetails deliveryDetails){
        return this.deliveryMapper.mapToDeliveryDetailsDTO(deliveryDetails);
    }

    @Override
    public DeliveryDetails mapDeliveryDetailsDTOToEntity(DeliveryDetailsDTO deliveryDetailsDTO,DeliveryDetails deliveryDetails) {
        return this.deliveryMapper.mapDeliveryDetailsDTOToEntity(deliveryDetailsDTO,deliveryDetails);
    }

    @Override
    public DeliveryDetails saveDeliveryDetails(DeliveryDetails deliveryDetails) {
        return deliveryDetailsRepository.saveAndFlush(deliveryDetails);
    }
}
