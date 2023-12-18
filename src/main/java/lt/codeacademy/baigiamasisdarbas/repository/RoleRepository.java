package lt.codeacademy.baigiamasisdarbas.repository;

import lt.codeacademy.baigiamasisdarbas.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleType(String roleType);
}
