package com.traffsys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.DTO.QCResultDto;
import com.traffsys.service.QCResultMasterServices;

@RestController
@RequestMapping("/QCResultMaster")
public class QCResultMasterController {

	@Autowired
	private QCResultMasterServices masterServices;
	@CrossOrigin
	@PostMapping("/add")
	public ResponseEntity<?>add(@RequestBody QCResultDto dto)
	{
		return ResponseEntity.ok(masterServices.addQulaity(dto));
		
	}
	@CrossOrigin
	@RequestMapping("/all")
	ResponseEntity<?> getAll(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int size)
	{
		Page<QCResultDto> dtoall=masterServices.allData(page,size);
		Map<String,Object> response= new HashMap<>();
		response.put("status", 200);
		response.put("message", "fetch All Data Successfully");
		response.put("myObjectList", dtoall.getContent());
		response.put("totalPages", dtoall.getTotalPages());
		response.put("totalElements", dtoall.getNumberOfElements());
		response.put("pageSize", dtoall.getPageable());
		response.put("currentPage", dtoall.getNumber());
		return ResponseEntity.ok(response);
		
	}
	@CrossOrigin
	@PutMapping("/update/{id}")
	ResponseEntity<?> updateData(@PathVariable Long id,@RequestBody QCResultDto dto)
	{
		return ResponseEntity.ok(masterServices.updateRowData(id,dto));
		
	}
	
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?>deleteRowData(@PathVariable Long id)
	{
		return ResponseEntity.ok(masterServices.deleteRow(id));
		
	}
	
}
