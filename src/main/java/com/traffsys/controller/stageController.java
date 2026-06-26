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

import com.traffsys.DTO.stageDto;
import com.traffsys.service.StageMasterService;
@RestController
@RequestMapping("/stage")
public class stageController 
{
	@Autowired
	private StageMasterService stageService;
	
	@CrossOrigin
	@PostMapping("/add")
	public ResponseEntity<?>addStage(@RequestBody stageDto stDto)
	{
		return ResponseEntity.ok(stageService.stageAdd(stDto));
		
	}
	
	
	@GetMapping("/record")
	public ResponseEntity<?>allStageData(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size)
	{
		Page<stageDto> stDto = stageService.getAllStageData(page,size);
		Map<String, Object> response = new HashMap<>();
		response.put("status",200);
		response.put("message", "Fetch All Record Successfully");
		response.put("myObjectList", stDto.getContent());
		response.put("currentPage", stDto.getNumber());
		response.put("pageSize", stDto.getSize());
		response.put("totalRecords", stDto.getTotalElements());
		response.put("totalPages", stDto.getTotalPages());
		return ResponseEntity.ok(response);
		
	}
	
	@CrossOrigin
    @PutMapping("/update/{id}")
	public ResponseEntity<?>updateStageData(@PathVariable Long id,@RequestBody stageDto sDto)
	{
		return ResponseEntity.ok(stageService.updateStagemaster(id,sDto));
		
	}
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteStageData(@PathVariable Long id)
	{
		return ResponseEntity.ok(stageService.deleteStageRowData(id));
		
	}

}
