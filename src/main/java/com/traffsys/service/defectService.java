package com.traffsys.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.traffsys.DTO.defectDto;
import com.traffsys.Repository.defectRepository;
import com.traffsys.Repository.userRepo;
import com.traffsys.entity.defectMaster;
import com.traffsys.entity.user;

@Service
public class defectService 
{
	@Autowired
	private defectRepository defectRepository;
	
	@Autowired userRepo userrepository;

	public Object addDefectDataMethod(defectDto defectDto) 
	{
		Map<String,Object> response = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		defectMaster df = defectRepository.findBydefectCode(defectDto.getDefectCode());
		
		if(df !=null)
		{
			response.put("status", 400);
			response.put("message", "decected code already exist");
			return response;
		}
		defectMaster dfData= new defectMaster();
		dfData.setDefectName(defectDto.getDefectName());
		dfData.setDefectCode(defectDto.getDefectCode());
		dfData.setDescription(defectDto.getDescription());
		dfData.setStatus(defectDto.getStatus());
		dfData.setCreatedDate(LocalDateTime.now());
		dfData.setCreatedBy(username);
		defectRepository.save(dfData);
		response.put("status", 200);
		response.put("message", "Added SuccessFully");
		
		return response;
	}

	public Page<defectDto> gettAllData(int page, int size)
	{
		Map<String, Object> response= new HashMap<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

	     user usernameforauth= userrepository.findByUsername(username);

		if (usernameforauth == null) {
			throw new RuntimeException("user Not Found");
		}
		
		if(!usernameforauth.getRole().getRoleName().equals("ROLE_ADMIN"))
		{
			response.put("status", 200);
			response.put("message", "user Name have not Role to get All data");
			return (Page<defectDto>) response;
		}

	    PageRequest pageable= PageRequest.of(page, size);
	    
	    Page<defectMaster>record= defectRepository.findAll(pageable);
	    return record.map(respon -> {
	    	defectDto dfto= new defectDto();
	    	dfto.setId(respon.getId());
	    	dfto.setDefectCode(respon.getDefectCode());
	    	dfto.setDefectName(respon.getDefectName());
	    	dfto.setDescription(respon.getDescription());
	    	dfto.setStatus(respon.getStatus());
	        return dfto;
	    });
		
	
		
	}

	public Object updateDefect(Long id, defectDto dfdto) 
	{
		Map<String,Object> response= new HashMap<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		
		defectMaster dfuserId=defectRepository.findById(id).orElseThrow(()-> new RuntimeException());
		dfuserId.setDefectCode(dfdto.getDefectCode());
		dfuserId.setDefectName(dfdto.getDefectName());
		dfuserId.setDescription(dfdto.getDescription());
		dfuserId.setCreatedBy(username);
		dfuserId.setUpdatedBy(username);
		dfuserId.setUpdatedDate(LocalDateTime.now());
		dfuserId.setStatus(dfdto.getStatus());
		defectRepository.save(dfuserId);
		
		response.put("status", 200);
		response.put("message", "defect Updated Successfully");
		
		return response;
	}

	public Object deleteMethod(Long id) 
	{
		Map<String, Object> response= new HashMap<>();
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		user userid = userrepository.findById(id).orElseThrow();
		defectMaster uid= defectRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
		defectRepository.delete(uid);
		if(userid == null)
		{
			response.put("status", 400);
			response.put("message", "user not valid");
			return response;
		}
		
			
			
		return "Deleted Successfully";
	}
	

}
