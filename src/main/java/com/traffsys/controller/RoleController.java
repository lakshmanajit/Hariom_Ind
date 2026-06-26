package com.traffsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.Authservices.RoleServices;
import com.traffsys.DTO.RoleAssignDto;
import com.traffsys.DTO.RolePrivilegeAssignDto;
import com.traffsys.entity.Role;

@RestController
@RequestMapping("/role")
public class RoleController 
{
	@Autowired
	private RoleServices roleservice;
	@CrossOrigin
	@PostMapping("/addRole")
	public ResponseEntity<?> add_Role(@RequestBody Role role)
	{
		
		return ResponseEntity.ok(roleservice.addRole(role));
		
	}
	

	@CrossOrigin
	@PostMapping("/assignRole_new")
	public ResponseEntity<Object> assingRole(@RequestBody RoleAssignDto assignDto)
	{
		 System.out.println("CONTROLLER HIT");
		return ResponseEntity.ok(roleservice.AssginRoleByadmin(assignDto));
		
	}
	
	@CrossOrigin
	@PostMapping("assignPrivilegeToROle")
	public ResponseEntity<?>privilegeAssingToRole(@RequestBody RolePrivilegeAssignDto privilegeAssignDto)
	{
		
		return ResponseEntity.ok(roleservice.assignPrivileges(privilegeAssignDto));
		
	}
	


}
