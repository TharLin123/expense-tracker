package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.edu.secondleave.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

}
