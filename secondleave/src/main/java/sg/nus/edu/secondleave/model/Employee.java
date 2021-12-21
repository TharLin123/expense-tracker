package sg.nus.edu.secondleave.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String name;
	private String username;
	private String password;
	
	private Integer managerId;
		
	@ManyToMany(targetEntity = Role.class, cascade = {CascadeType.ALL, CascadeType.PERSIST})
	@JoinTable(name="emp_role",joinColumns = @JoinColumn(name="employeeId"), inverseJoinColumns
			=@JoinColumn(name="roleId"))
	private Set<Role> roles;
	
	@OneToMany(targetEntity = LeaveApplication.class, mappedBy = "employee")
	private Collection<LeaveApplication> leaves;
	
	@OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true,
        mappedBy="employee")
	private Collection<LeaveEntitlement> leaveEntitlements = new ArrayList<LeaveEntitlement>();

	public Employee(String name, String username, String password, Integer managerId, Set<Role> roles,
			Collection<LeaveApplication> leaves, Collection<LeaveEntitlement> leaveEntitlements) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.managerId = managerId;
		this.roles = roles;
		this.leaves = leaves;
		this.leaveEntitlements = leaveEntitlements;
	}


	
//	
//	@OneToMany
//	private Set<Employee> subordinates; 
	
	

	
}

