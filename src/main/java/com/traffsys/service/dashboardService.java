package com.traffsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffsys.DTO.DashboardDto;
import com.traffsys.Repository.ProductRepo;
import com.traffsys.Repository.QCResultDtoInterface;
import com.traffsys.Repository.defectRepository;
import com.traffsys.Repository.inspectionMasterInterface;
import com.traffsys.Repository.lotBatchInterface;
import com.traffsys.Repository.stageRepo;

@Service
public class dashboardService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private stageRepo stageRepo;
	@Autowired
	private defectRepository defectRepository;
	@Autowired
	private inspectionMasterInterface inspectionMasterInterface;
	@Autowired
	private lotBatchInterface batchInterface;
	
	@Autowired
	private QCResultDtoInterface dtoInterface;
	
	
	public Object getDashboardMethod() 
	{
		DashboardDto dasDto= new DashboardDto();
		dasDto.setTotalProducts(productRepo.count());
		dasDto.setTotalStages(stageRepo.count());
		dasDto.setTotalDefects(defectRepository.count());
		dasDto.setTotalInspections(inspectionMasterInterface.count());
		dasDto.setTotalLotBatches(batchInterface.count());
		dasDto.setTotalQcResults(dtoInterface.count());
		dasDto.setPassCount(dtoInterface.countByResultStatus("PASS"));
		dasDto.setFailCount(dtoInterface.countByResultStatus("FAIL"));
		return dasDto;
	}

}
