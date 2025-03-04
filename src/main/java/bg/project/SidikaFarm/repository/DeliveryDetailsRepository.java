package bg.project.SidikaFarm.repository;

import bg.project.SidikaFarm.model.entity.DeliveryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetails,Long> {
}
