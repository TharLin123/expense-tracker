package sg.nus.edu.secondleave.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="holiday_list")
public class Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="The holiday name can not be null")
	@Size(min=6, max=50, message="Holiday name must be between {min} and {max} characters long.")
	private String name;
	
	private int year;
	
	@Future
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date);
	}

	@Override
	// if two holidays have the same date, we treat them as the same holiday
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Holiday other = (Holiday) obj;
		return Objects.equals(date, other.date);
	}

	@Override
	public String toString() {
		return "Holiday [id=" + id + ", name=" + name + ", year=" + year + ", date=" + date + "]";
	}
	
}