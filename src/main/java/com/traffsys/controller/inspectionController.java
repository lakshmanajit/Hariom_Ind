package com.traffsys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.traffsys.DTO.inspectionMasterDto;
import com.traffsys.entity.InspectionMaster;
import com.traffsys.service.inspectionMasterServices;
@RestController
@RequestMapping("/inspection")
public class inspectionController {
	
	@Autowired
	private inspectionMasterServices inspectionMasterServices;
	
	
	@PostMapping("/add")
	public ResponseEntity<?>addInspectionData(@RequestBody inspectionMasterDto dto)
	{
		return ResponseEntity.ok(inspectionMasterServices.addInspection(dto));
		
	}
	
	@CrossOrigin
	@GetMapping("/all")
	public ResponseEntity<?>allRecordwithPagination(@RequestParam(defaultValue = "0")int page , @RequestParam(defaultValue = "10")int size)
	{
		Page<inspectionMasterDto> iDto = inspectionMasterServices.findAllDataFromDb(page,size);
		Map<String,Object> response = new HashMap<>();
		
		response.put("myObjectList", iDto.getContent());
		response.put("pageNumber", iDto.getNumber());
		response.put("page", iDto.getPageable());
		response.put("totalPages", iDto.getTotalPages());
		response.put("totalRecords", iDto.getTotalElements());
		
		response.put("status",200);
		response.put("message", "All Record fetch To DB");
		return ResponseEntity.ok(response);
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateInspect(@PathVariable Long id, @RequestBody inspectionMasterDto dto) {
		return ResponseEntity.ok(inspectionMasterServices.updateIns(id, dto));

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteMethod(@PathVariable Long id)
	{
		return ResponseEntity.ok(inspectionMasterServices.deleteInsMethod(id));
		
	}
	 
	
	
	/*
	 * @PutMapping("/update/{id}") public
	 * ResponseEntity<?>updateInspect(@RequestBody InspectionMaster inmaster ) {
	 * return ResponseEntity.ok(inspectionMasterServices.updateIns(inmaster));
	 * 
	 * }
	 */
	
	

}
