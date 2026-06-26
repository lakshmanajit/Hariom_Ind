package com.traffsys.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.Authservices.AuthServices;
import com.traffsys.DTO.RegisterDto;
import com.traffsys.DTO.RoleAssignDto;
import com.traffsys.DTO.UserResponse;
import com.traffsys.DTO.loginDto;
import com.traffsys.entity.user;

@RestController
public class loginController 
{
	@Autowired
	private AuthServices authServices;
	@CrossOrigin
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody loginDto dto)
	{
		
		return ResponseEntity.ok(authServices.login(dto));
		
	}
	
	@PostMapping("/auth/refresh")
	public ResponseEntity<?>refreshTokken(@RequestParam String username,@RequestParam String refreshtokken)
	{
		return ResponseEntity.ok(authServices.refresh(username, refreshtokken));
		
	}
	@CrossOrigin
	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterDto dto) {
	    return ResponseEntity.ok(authServices.registerUser(dto));
	}

	
	@CrossOrigin
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {

	    Page<UserResponse> users =
	            authServices.getAllUsers(page, size);

	    Map<String, Object> response = new HashMap<>();

	    response.put("status", 200);
	    response.put("message", "Success");

	    response.put("myObjectList", users.getContent());

	    response.put("currentPage", users.getNumber());
	    response.put("pageSize", users.getSize());
	    response.put("totalRecords", users.getTotalElements());
	    response.put("totalPages", users.getTotalPages());

	    return ResponseEntity.ok(response);
	}
	
    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody RegisterDto dto) {
    	System.out.println("hello update ");
        return ResponseEntity.ok(authServices.updateUser(id, dto));
    }
    
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(authServices.deleteUser(id));
    }
    


}
