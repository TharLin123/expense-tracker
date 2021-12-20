package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.edu.secondleave.model.LeaveEntitlement;

public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, Integer> {

}
