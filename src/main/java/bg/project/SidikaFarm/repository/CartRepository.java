package bg.project.SidikaFarm.repository;

import bg.project.SidikaFarm.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartById(Long id);
}
