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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
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
	
	@OneToMany(targetEntity = LeaveApplication.class, cascade = CascadeType.ALL,
	        orphanRemoval = true,mappedBy = "employee")
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
//		this.leaves = leaves;
		this.leaveEntitlements = leaveEntitlements;
	}

	//Getters and Setters to avoid StackOverFlow caused By @Data
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Collection<LeaveApplication> getLeaves() {
		return leaves;
	}

	public void setLeaves(Collection<LeaveApplication> leaves) {
		this.leaves = leaves;
	}

	public Collection<LeaveEntitlement> getLeaveEntitlements() {
		return leaveEntitlements;
	}

	public void setLeaveEntitlements(Collection<LeaveEntitlement> leaveEntitlements) {
		this.leaveEntitlements = leaveEntitlements;
	}




	
//	
//	@OneToMany
//	private Set<Employee> subordinates; 
	
	

	
}

