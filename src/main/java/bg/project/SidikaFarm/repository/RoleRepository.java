package bg.project.SidikaFarm.repository;

import bg.project.SidikaFarm.model.entity.Role;
import bg.project.SidikaFarm.model.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleType(RoleType roleType);
}
