package com.traffsys.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.traffsys.DTO.stageDto;
import com.traffsys.Repository.stageRepo;
import com.traffsys.Repository.userRepo;
import com.traffsys.entity.StageMaster;
import com.traffsys.entity.user;

@Service
public class StageMasterService 
{

	@Autowired
	private stageRepo repo;
	
	@Autowired
	private userRepo userRepository;
	
	public Object stageAdd(stageDto stDto)
	{
		Map<String,Object> response = new HashMap<>();
		
		StageMaster stageCode = repo.findByStageCode(stDto.getStageCode());
		
		if(stageCode !=null)
		{
			response.put("status", 400);
			response.put("message", "Stage COde  Already exist");
			return response;
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		StageMaster stage_entity= new StageMaster();
		stage_entity.setStageName(stDto.getStageName());
		stage_entity.setStageCode(stDto.getStageCode());
		stage_entity.setCreatedBy(username);
		stage_entity.setCreatedDate(LocalDateTime.now());
		stage_entity.setDescription(stDto.getDescription());
		stage_entity.setStatus(stDto.getStatus());
		repo.save(stage_entity);
		response.put("status",200);
		response.put("message", "stage Master Added SuccessFully");
		return response;
	}
	
	public Page<stageDto> getAllStageData(int page, int size) 
	{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		user currentUseMaster=userRepository.findByUsername(username);
		
		if(currentUseMaster == null)
		{
			throw new RuntimeException("username Not Present In Request Body");
		}
		if(currentUseMaster.getRole().getRoleName().equals(currentUseMaster))
		{
			throw new RuntimeException("username have not Privilege to fetch Stage Data");
		}
		PageRequest pageable = PageRequest.of(page, size);
		Page<StageMaster> pageResponse= repo.findAll(pageable);
		return  pageResponse.map(StageMaster ->{
			stageDto st= new stageDto();
			st.setStageCode(StageMaster.getStageCode());
			st.setStageName(StageMaster.getStageName());
			st.setDescription(StageMaster.getDescription());
			st.setId(StageMaster.getId());
			st.setCreatedBy(StageMaster.getCreatedBy());
			st.setStatus(StageMaster.getStatus());
			return st;
		});
		
	}

	public Object updateStagemaster(Long id, stageDto sDto) 
	{
		Map<String, Object> response= new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
	    StageMaster strowData= repo.findById(id).orElseThrow(() ->new  RuntimeException("stage Not Found"));
	     
	     strowData.setStageName(sDto.getStageName());
	     strowData.setStageCode(sDto.getStageCode());
	     strowData.setDescription(sDto.getDescription());
	     strowData.setUpdatedBy(username);
	     strowData.setUpdatedDate(LocalDateTime.now());
	     strowData.setStatus(sDto.getStatus());
	     
	     repo.save(strowData);
	     
	    response.put("status", 200);
	    response.put("message", "Done SuccessFully");
	     
		return response;
	}

	public Object deleteStageRowData(Long id)
	{
		Map<String,Object> response= new HashMap<>();
		StageMaster sid= repo.findById(id).orElseThrow(()-> new RuntimeException("stage row data not Found"));
		repo.delete(sid);
		response.put("status", 200);
		response.put("message","deleted selected row data");
		return response;
	}
	

}
