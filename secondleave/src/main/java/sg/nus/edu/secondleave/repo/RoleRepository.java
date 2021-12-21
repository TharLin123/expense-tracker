package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.edu.secondleave.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String rolename);
	
	@Query("select r from Role r where r.roleId =:roleId")
	public Role findRole(@Param("roleId") Integer roleId);
}
