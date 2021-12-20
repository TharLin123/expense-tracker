package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.edu.secondleave.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
