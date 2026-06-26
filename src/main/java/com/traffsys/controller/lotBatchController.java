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

import com.traffsys.DTO.LotBatchDto;
import com.traffsys.service.lotBatchService;
@RestController
@RequestMapping("/lotbatch")
public class lotBatchController 
{
	@Autowired
	private lotBatchService batchService;
	
	@CrossOrigin
	@PostMapping("/add")
	public ResponseEntity<?>addLotBatchData(@RequestBody LotBatchDto batchDto)
	{
		return ResponseEntity.ok(batchService.addBatchData(batchDto));
		
	}
	
	@CrossOrigin
	@GetMapping("/all")
	public ResponseEntity<?>all(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size )
	{
		Page<LotBatchDto> stdata= batchService.getAllData(page, size);
		Map<String,Object> response= new HashMap<>();
		
		response.put("status", 200);
		response.put("message", "All record Fetch");
		
		response.put("myObjectlist", stdata.getContent());
		response.put("totalPages", stdata.getTotalPages());
		response.put("totalElements", stdata.getTotalElements());
		response.put("currentPage", stdata.getNumber());
		response.put("pageSize", stdata.getSize());
		
		return ResponseEntity.ok(response);
	}
	
	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatelotData(@PathVariable Long id, @RequestBody LotBatchDto dto)
	{
		System.out.println("hit update controller********");
		return ResponseEntity.ok(batchService.updateRowData(id,dto));
		
	}
	
	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMethod(@PathVariable Long id)
	{
		return ResponseEntity.ok(batchService.deletelot(id));
		
	}

}
