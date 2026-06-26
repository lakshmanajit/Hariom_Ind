package com.traffsys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
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

import com.traffsys.DTO.defectDto;
import com.traffsys.service.defectService;

@RestController
@RequestMapping("/defect")
public class defectController 
{
	
	@Autowired
	private defectService defectService;
	
	@CrossOrigin
	@PostMapping("/add")
	public ResponseEntity<?>addDefectData(@RequestBody defectDto defectDto )
	{
		return ResponseEntity.ok(defectService.addDefectDataMethod(defectDto));
		
	}
	@CrossOrigin
	@GetMapping("/all")
	public ResponseEntity<?>getAllDefectData(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue="10")int size)
	{
		Page<defectDto> df =defectService.gettAllData(page,size);
		Map<String,Object> response=new HashMap<>();
		response.put("status", 200);
		response.put("message", "Fetch All Data Successfully");
		response.put("myObjectList", df.getContent());
		response.put("currentPage", df.getNumber());
		response.put("totalPages",df.getTotalPages());
		response.put("totalElements", df.getTotalElements());
		response.put("pageSize", df.getPageable());
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?>updateData(@PathVariable Long id, @RequestBody defectDto dfdto)
	{
		return ResponseEntity.ok(defectService.updateDefect(id,dfdto));
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDefect(@PathVariable Long id)
	{
		return ResponseEntity.ok(defectService.deleteMethod(id));
		
	}

}
