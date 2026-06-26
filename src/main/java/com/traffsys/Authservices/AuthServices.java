package com.traffsys.Authservices;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.traffsys.DTO.RegisterDto;
import com.traffsys.DTO.RoleAssignDto;
import com.traffsys.DTO.TokenReponse;
import com.traffsys.DTO.UserResponse;
import com.traffsys.DTO.loginDto;
import com.traffsys.JWT.JwtUtill;
import com.traffsys.Repository.RoleRepo;
import com.traffsys.Repository.userRepo;
import com.traffsys.entity.Role;
import com.traffsys.entity.user;
import com.traffsys.token.TokenResponse;

@Service
public class AuthServices {

	@Autowired
	private userRepo repo;

	@Autowired
	private JwtUtill jwtUtill;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public TokenReponse login(loginDto dto) {

		System.out.println("Input Username: " + dto.getUsername());
		System.out.println("Input Password: " + dto.getPassword());

		user user_table = repo.findByUsername(dto.getUsername());

		if (user_table == null) {
			throw new RuntimeException("User not found");
		}

		System.out.println("DB Password: " + user_table.getPassword());

		// BCrypt password check
		boolean isMatch = passwordEncoder.matches(dto.getPassword(), user_table.getPassword());

		if (!isMatch) {
			throw new RuntimeException("Invalid Credentials");
		}

		String accessToken = jwtUtill.generateToken(user_table.getUsername());

		String refreshToken = UUID.randomUUID().toString();

		user_table.setRefreshTokken(refreshToken);

		repo.save(user_table);

		return new TokenReponse(accessToken, refreshToken, user_table.getRole());
	}

	public TokenResponse refresh(String username, String refreshToken) {

		user user_table = repo.findByUsername(username);

		if (user_table == null || !refreshToken.equals(user_table.getRefreshTokken())) {
			throw new RuntimeException("Invalid Refresh Token");
		}

		String newAccess = jwtUtill.generateToken(username);
		String newRefresh = UUID.randomUUID().toString();

		user_table.setRefreshTokken(newRefresh);
		repo.save(user_table);

		return new TokenResponse(newAccess, newRefresh);
	}

	public Object registerUser(RegisterDto dto) {

	    Map<String, Object> response = new HashMap<>();

	    user existingUser = repo.findByUsername(dto.getUsername());

	    if (existingUser != null) {

	        response.put("status", 400);
	        response.put("message", "User already exists");

	        return response;
	    }

	    Role role = roleRepo.findByRoleName(dto.getRole());

	    if (role == null) {

	        response.put("status", 404);
	        response.put("message", "Role not found");

	        return response;
	    }

	    user newUser = new user();

	    newUser.setUsername(dto.getUsername());
	    newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
	    newUser.setEmail(dto.getEmail());
	    newUser.setMobile(dto.getMobile());
	    newUser.setRole(role);

	    repo.save(newUser);

	    response.put("status", 200);
	    response.put("message", "User Registered Successfully");

	    return response;
	}



	public String updateUser(Long id, RegisterDto dto) {

		// user find from repo
		user existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		// fields update
		existingUser.setUsername(dto.getUsername());
		existingUser.setPassword(dto.getPassword());
		existingUser.setEmail(dto.getEmail());
		existingUser.setMobile(dto.getMobile());

		// save
		repo.save(existingUser);

		return "User Updated Successfully";
	}

	public String deleteUser(Long id) {

		// 1. check user exist
		user existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		// 2. delete
		repo.delete(existingUser);

		return "User Deleted Successfully";
	}

	public Page<UserResponse> getAllUsers(int page, int size) {

		PageRequest pageable = PageRequest.of(page, size);

		Page<user> users = repo.findAll(pageable);

		return users.map(u -> {

			UserResponse res = new UserResponse();

			res.setId(u.getId());
			res.setUsername(u.getUsername());
			res.setEmail(u.getEmail());
			res.setMobile(u.getMobile());

			return res;
		});
	}

	/*
	 * public Object roleAssingkaro(RoleAssignDto dto) { Map<String,Object>
	 * response= new HashMap<>(); user
	 * userObj=repo.findById(dto.getUserId()).orElse(null);
	 * 
	 * if(userObj== null) { response.put("status", 404);
	 * response.put("message","user Not Found"); return response; }
	 * 
	 * if(userObj.getRole() != null &&
	 * userObj.getRole().getId().equals(dto.getRoleId())) {
	 * 
	 * response.put("status", 400); response.put("message",
	 * "Role already assigned");
	 * 
	 * return response; }
	 * 
	 * Role role = roleRepo.findById(dto.getRoleId()).orElse(null);
	 * 
	 * if(role == null) { response.put("status", 404); response.put("message",
	 * "Role not Found"); return response; }
	 * 
	 * 
	 * userObj.setRole(role); repo.save(userObj); response.put("status",200);
	 * response.put("message","Role Assigned Successfully");
	 * 
	 * 
	 * return response; }
	 */
}