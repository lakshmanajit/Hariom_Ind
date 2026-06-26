package com.traffsys.Authservices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffsys.DTO.RoleAssignDto;
import com.traffsys.DTO.RolePrivilegeAssignDto;
import com.traffsys.Repository.MasterPrivilegeRepo;
import com.traffsys.Repository.RoleRepo;
import com.traffsys.Repository.userRepo;
import com.traffsys.entity.MasterPrivilege;
import com.traffsys.entity.Role;
import com.traffsys.entity.user;

@Service
public class RoleServices {

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private userRepo repo;
	
	@Autowired
	private MasterPrivilegeRepo masterPrivilegeRepo;
	
	public Object addRole(Role role) {
		//Check duplicate role
		 Role existingRole =roleRepo.findByRoleName(role.getRoleName());
		 
		 if(existingRole != null) {
	            throw new RuntimeException("Role already exists");
	        }
		 
		// save role
	        roleRepo.save(role);
	        Map<String, Object> response =
	                new HashMap<>();

	        response.put("status", 200);
	        response.put("message", "Role Added Successfully");
	        response.put("data", role);
	        return response;
	}



	
	public Object AssginRoleByadmin(RoleAssignDto assignDto) 
	{

		Map<String,Object> response= new HashMap<>();
	    user userObj=repo.findById(assignDto.getUserId()).orElse(null);
	    
	    if(userObj== null)
	    {
	    	response.put("status", 404);
	    	response.put("message","user Not Found");
	    	return response;
	    }
	    
	    if(userObj.getRole() != null &&
	    		   userObj.getRole().getId().equals(assignDto.getRoleId())) {

	    		    response.put("status", 400);
	    		    response.put("message", "Role already assigned");

	    		    return response;
	    }
	    
	    Role role = roleRepo.findById(assignDto.getRoleId()).orElse(null);
	    
	    if(role == null)
	    {
	    	response.put("status", 404);
	    	response.put("message", "Role not Found");
	    	return response;
	    }
	    
	   
	    userObj.setRole(role);
	    repo.save(userObj);
	    response.put("status",200);
	    response.put("message","Role Assigned Successfully");
	    
	    
		return response;
	}




	public Object assignPrivileges(RolePrivilegeAssignDto privilegeAssignDto)
	{
		Map<String,Object> response = new HashMap<>();
		
		Role role=roleRepo.findById(privilegeAssignDto.getRoleId()).orElse(null);
		
		if(role==null)
		{
			response.put("status",404);
			response.put("message","Role Not Found");
			return response;
		}
		
		List<MasterPrivilege> assignMultiple=masterPrivilegeRepo.findAllById(privilegeAssignDto.getPrivilegeIds());
		
		role.setPrivileges(assignMultiple);
		
		roleRepo.save(role);
		response.put("status", 200);
		response.put("message", "Privilege Assinged Successfully");
		
		return response;
	}

}
