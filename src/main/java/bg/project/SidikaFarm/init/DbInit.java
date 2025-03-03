package bg.project.SidikaFarm.init;

import bg.project.SidikaFarm.model.entity.Role;
import bg.project.SidikaFarm.model.entity.enums.RoleType;
import bg.project.SidikaFarm.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DbInit implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DbInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0){

            Arrays.stream(RoleType.values())
                    .forEach(roleType -> {
                        if (roleRepository.findByRoleType(roleType).isEmpty()) {
                            Role role = new Role();
                            role.setRoleType(roleType);
                            roleRepository.save(role);
                        }
                    });
            System.out.println("Roles initialized successfully!");
        }
    }
}
