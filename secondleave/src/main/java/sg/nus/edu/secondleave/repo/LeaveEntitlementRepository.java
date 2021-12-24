package sg.nus.edu.secondleave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.edu.secondleave.model.Employee;
import sg.nus.edu.secondleave.model.LeaveEntitlement;
import sg.nus.edu.secondleave.util.TypeEnum;


@Repository
public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, Integer> {
	LeaveEntitlement findTopByEmployeeAndType(Employee emp, TypeEnum type);
	List<LeaveEntitlement> findByEmployee(Employee emp);
}
