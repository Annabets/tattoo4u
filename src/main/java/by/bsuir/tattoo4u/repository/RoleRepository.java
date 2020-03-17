package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
