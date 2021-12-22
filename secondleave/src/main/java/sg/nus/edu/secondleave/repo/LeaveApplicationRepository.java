package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.edu.secondleave.model.LeaveApplication;
import sg.nus.edu.secondleave.util.LeaveEnum;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	
//	@Transactional
//	@Modifying
//	@Query("SELECT lap FROM leave_application lap INNER JOIN employee e ON lap.employee_id = e.employee_id")
//	public List<LeaveApplication> findLAPByEmployeeId(@Param("id") Integer id );
	
	@Transactional
	@Modifying
	@Query(value="Update leave_application lap SET lap.status = :leaveEnum where lap.leave_app_id = :leaveId",nativeQuery = true)
    public void updateLeaveApplication(@Param("leaveId") int leaveId, @Param("leaveEnum") String leaveEnum);
}
