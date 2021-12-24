package sg.nus.edu.secondleave.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.edu.secondleave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("select e from Employee e where e.username = :username and e.password = :password")
	Employee authenticate (@Param("username") String username, @Param("password") String password);
	
	Employee findByemployeeId(int id);
	
	@Query("select distinct e.employeeId from Employee e")
	List<String> findAllemployeeIds();

	@Modifying
	@Query("delete from Employee e where e.employeeId = :employeeId")
	void deleteEmp(@Param("employeeId") int EmployeeId);
	
	//For AdminManageLeaveController (Kenny)
		@Transactional
		@Modifying
		@Query(value = "update leave_entitlement set entitlement = :entitlementvalue where type = 'ANNUAL' and employee_employee_id = :employeeId",nativeQuery=true)
		public void updateAnnualValue(@Param("entitlementvalue") Integer entitlementvalue, @Param("employeeId") Integer employeeId);

		@Transactional
		@Modifying
		@Query(value = "update leave_entitlement set entitlement = :entitlementvalue where type = 'MEDICAL' and employee_employee_id = :employeeId",nativeQuery=true)
		public void updateMedicalValue(@Param("entitlementvalue") Integer entitlementvalue, @Param("employeeId") Integer employeeId);
		
		@Transactional
		@Modifying
		@Query(value = "update leave_entitlement set entitlement = :entitlementvalue where type = 'COMPENSATION' and employee_employee_id = :employeeId",nativeQuery=true)
		public void updateCompValue(@Param("entitlementvalue") Integer entitlementvalue, @Param("employeeId") Integer employeeId);

}
