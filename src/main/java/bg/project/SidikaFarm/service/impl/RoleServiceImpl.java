package bg.project.SidikaFarm.service.impl;

import bg.project.SidikaFarm.model.entity.Role;
import bg.project.SidikaFarm.model.entity.enums.RoleType;
import bg.project.SidikaFarm.repository.RoleRepository;
import bg.project.SidikaFarm.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

public Role findByRoleType(RoleType roleType){
      return roleRepository
                .findByRoleType(roleType)
                .orElseThrow(() -> new RuntimeException("Role" + roleType + "not found"));
}
}
