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

import com.traffsys.DTO.LotBatchDto;
import com.traffsys.Repository.lotBatchInterface;
import com.traffsys.entity.LotBatchMaster;
import com.traffsys.entity.Product;

@Service
public class lotBatchService {

	@Autowired
	private lotBatchInterface batchInterface;
	
	
	public Object addBatchData(LotBatchDto batchDto) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		Map<String, Object> response= new HashMap<>();
		LotBatchMaster lotid =
		        batchInterface.findByLotNumber(batchDto.getLotNumber());
		
		Product productData= batchDto.getmProduct();
		
		if(lotid !=null)
		{
			response.put("status", 400);
			response.put("message", "Lot Number Already Exists");
			return response;
		}
		LotBatchMaster lot= new LotBatchMaster();
		
		lot.setDescription(batchDto.getDescription());
		
		lot.setCreatedBy(username);
        lot.setCreatedDate(LocalDateTime.now());	
        lot.setExpiryDate(batchDto.getExpiryDate());
        lot.setLotNumber(batchDto.getLotNumber());
        lot.setQuantity(batchDto.getQuantity());
        lot.setmProduct(productData);
        lot.setStatus(batchDto.getStatus());
        lot.setManufacturingDate(batchDto.getManufacturingDate());
        batchInterface.save(lot);
        
        response.put("status", 200);
        response.put("message", "lotBatch Data Added Successfully");
		
		return response;
	}


	public Page<LotBatchDto> getAllData(int page, int size) 
	{
		PageRequest pageData=  PageRequest.of(page, size);
		Page<LotBatchMaster> response= batchInterface.findAll(pageData);
		return response.map(LotBatchMaster ->{
			LotBatchDto lbt = new LotBatchDto();
			lbt.setId(LotBatchMaster.getId());
			lbt.setLotNumber(LotBatchMaster.getLotNumber());
			lbt.setManufacturingDate(LotBatchMaster.getManufacturingDate());
			lbt.setDescription(LotBatchMaster.getDescription());
			lbt.setQuantity(LotBatchMaster.getQuantity());
			lbt.setmProduct(LotBatchMaster.getmProduct());
			lbt.setExpiryDate(LotBatchMaster.getExpiryDate());
			lbt.setStatus(LotBatchMaster.getStatus());
			
			
			return lbt;
		});
		
	}


	public Object updateRowData(Long id, LotBatchDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		//LotBatchMaster lotid= batchInterface.findLotId(dto.getId());
		LotBatchMaster lotid = batchInterface.findById(id)
		        .orElseThrow(() -> new RuntimeException("Lot Batch Not Found"));
		System.out.println("lotbatch id **************"+lotid);
		lotid.setLotNumber(dto.getLotNumber());
		lotid.setDescription(dto.getDescription());
		lotid.setUpdatedBy(username);
		lotid.setUpdatedDate(LocalDateTime.now());
		lotid.setQuantity(dto.getQuantity());
		
		lotid.setmProduct(dto.getmProduct());
		batchInterface.save(lotid);
		Map<String,Object> response= new HashMap<>();
		response.put("status", 200);
		response.put("message", "Updated Successfully");
		return response;
	}


	public Object deletelot(Long id)
	{
		LotBatchMaster lotid = batchInterface.findById(id)
		        .orElseThrow(() -> new RuntimeException("Lot Batch Not Found"));
		batchInterface.delete(lotid);
		return "Delete SuccessFuly";
	}

}
