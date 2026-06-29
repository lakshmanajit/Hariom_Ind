package com.traffsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traffsys.service.dashboardService;

@RestController
@RequestMapping("/home")
public class dashBoardController 
{
	@Autowired
	private dashboardService dsService;
	
	
	@GetMapping("/dashboard")
	public ResponseEntity<?>getDashboard()
	{
		return ResponseEntity.ok(dsService.getDashboardMethod());
		
	}

}
