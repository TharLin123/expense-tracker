package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.edu.secondleave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("select e from Employee e where e.username = :username and e.password = :password")
	Employee authenticate (@Param("username") String username, @Param("password") String password);
}
