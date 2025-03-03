package bg.project.SidikaFarm.service.interfaces;

import bg.project.SidikaFarm.model.entity.Role;
import bg.project.SidikaFarm.model.entity.enums.RoleType;

public interface RoleService {
     Role findByRoleType(RoleType roleType);
}
