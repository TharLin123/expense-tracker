package sg.nus.edu.secondleave.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.nus.edu.secondleave.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
	@Query("SELECT h.date FROM Holiday h")
	List<LocalDate> findAllDate();
}
