package sg.nus.edu.secondleave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.edu.secondleave.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	//JPQL NOT WORKING, USED NATIVE QUERY INSTEAD
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM leave_application lap WHERE lap.employee_employee_id = :id"
			+ " AND lap.status = 'APPROVED' OR lap.status = 'REJECTED'", nativeQuery = true)
	public List<LeaveApplication> findLAPByEmployeeId(@Param("id") Integer id );
	
	@Transactional
	@Modifying
	@Query(value="Update leave_application lap SET lap.status = :leaveEnum where lap.leave_app_id = :leaveId",nativeQuery = true)
    public void updateLeaveApplication(@Param("leaveId") int leaveId, @Param("leaveEnum") String leaveEnum);
}
