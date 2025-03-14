package bg.project.SidikaFarm.repository;

import bg.project.SidikaFarm.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> getProductById(Long id);
}
