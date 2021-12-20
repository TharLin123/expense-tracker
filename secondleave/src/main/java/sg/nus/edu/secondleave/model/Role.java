package sg.nus.edu.secondleave.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	private String name;
	private String description;
	
	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
}
