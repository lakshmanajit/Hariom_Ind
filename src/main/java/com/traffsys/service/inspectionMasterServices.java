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

import com.traffsys.DTO.inspectionMasterDto;
import com.traffsys.Repository.ProductRepo;
import com.traffsys.Repository.inspectionMasterInterface;
import com.traffsys.Repository.stageRepo;
import com.traffsys.Repository.userRepo;
import com.traffsys.entity.InspectionMaster;
import com.traffsys.entity.Product;
import com.traffsys.entity.StageMaster;
import com.traffsys.entity.user;

@Service
public class inspectionMasterServices {

	@Autowired
	private inspectionMasterInterface inspectionMasterInterface;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private stageRepo stageRepo;
	
	@Autowired
	private userRepo userepo;
	
	
	
	
	public Object addInspection(inspectionMasterDto dto)
	{
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		Map<String, Object> response= new HashMap<>();
		InspectionMaster inspectionid= inspectionMasterInterface.findByInspectionCode(dto.getInspectionCode());
		
		Product product= dto.getmProduct();
		StageMaster stagedata=dto.getmStage();
		
		if(inspectionid != null)
		{
			response.put("status", 400);
			response.put("message", "inspection Code already exit");
			
			return response;
		}
		 
		InspectionMaster inspect= new InspectionMaster();
		
		inspect.setInspectionName(dto.getInspectionName());
        inspect.setInspectionCode(dto.getInspectionCode());
        inspect.setDescription(dto.getDescription());
        inspect.setCreatedBy(username);
        inspect.setCreatedDate(LocalDateTime.now());
        inspect.setmProduct(product);
        inspect.setmStage(stagedata);
        inspect.setStatus(dto.getStatus());
        
        inspectionMasterInterface.save(inspect);
        
        response.put("status", 200);
        response.put("message", "Add InspectionData Successfully");
        
       
		return response;
	}


	public Page<inspectionMasterDto> findAllDataFromDb(int page, int size) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		Map<String,Object> response= new HashMap<>();
		
		user loginuser= userepo.findByUsername(username);
		
		if(loginuser==null)
		{
			throw new RuntimeException("User Name Not In Request Body");
		}
		
		if(loginuser.getRole().getRoleName().equals(loginuser))
		{
			throw new RuntimeException(" its only Admin Not For user");
		}
		PageRequest pageable = PageRequest.of(page, size);
		Page<InspectionMaster> pageresponse = inspectionMasterInterface.findAll(pageable);
		
		return pageresponse.map(InspectionMaster ->{
			inspectionMasterDto dto = new inspectionMasterDto();
			dto.setId(InspectionMaster.getId());
			dto.setInspectionName(InspectionMaster.getInspectionName());
			dto.setInspectionCode(InspectionMaster.getInspectionCode());
			dto.setDescription(InspectionMaster.getDescription());
			dto.setmProduct(InspectionMaster.getmProduct());
			dto.setmStage(InspectionMaster.getmStage());
			return dto;
		});
		
	}


	public Object updateIns(Long id, inspectionMasterDto dto) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		Map<String, Object> response = new HashMap<>();
		InspectionMaster insid = inspectionMasterInterface.findById(id).orElseThrow(()->new RuntimeException("inspection id not found"));
		insid.setInspectionName(dto.getInspectionName());
		insid.setInspectionCode(dto.getInspectionCode());
		insid.setDescription(dto.getDescription());
		insid.setStatus(dto.getStatus());
		insid.setmProduct(dto.getmProduct());
		insid.setmStage(dto.getmStage());
		insid.setUpdatedBy(username);
		insid.setUpdatedDate(LocalDateTime.now());
		inspectionMasterInterface.save(insid);
		
		response.put("status", 200);
		response.put("message","updated Successfully");
		
		
		return response;
	}


	public Object deleteInsMethod(Long id) 
	{
		Map<String, Object> response= new HashMap<>();
		InspectionMaster ins= inspectionMasterInterface.findById(id).orElseThrow();
		inspectionMasterInterface.delete(ins);
		response.put("status",200);
		response.put("message", "deleted Successfully");
		return response;
	}


	

}
