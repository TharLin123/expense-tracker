package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.edu.secondleave.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	
//	@Transactional
//	@Modifying
//	@Query("SELECT * FROM leave_application lap INNER JOIN employee e ON lap.employee_id = e.employee_id")
//	public List<LeaveApplication> findByEmployeeId(@Param("id") Integer id );
}
