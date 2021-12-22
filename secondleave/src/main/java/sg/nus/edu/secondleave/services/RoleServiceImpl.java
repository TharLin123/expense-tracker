package sg.nus.edu.secondleave.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.secondleave.model.Role;
import sg.nus.edu.secondleave.repo.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepo;
	@Override
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}

}
