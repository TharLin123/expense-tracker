package sg.nus.edu.secondleave.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.nus.edu.secondleave.util.TypeEnum;

@Entity
@Data
@NoArgsConstructor
public class LeaveEntitlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveEntitlementId;
	
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	
	private Double entitlement;
	
	@ManyToOne
	private Employee employee;
	
}
