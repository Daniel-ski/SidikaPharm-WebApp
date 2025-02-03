package bg.project.SidikaFarm.model.entity;

import bg.project.SidikaFarm.model.entity.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role() {
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
