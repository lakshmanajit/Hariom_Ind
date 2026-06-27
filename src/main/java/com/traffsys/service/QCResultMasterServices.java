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

import com.traffsys.DTO.QCResultDto;
import com.traffsys.Repository.QCResultDtoInterface;
import com.traffsys.Repository.defectRepository;
import com.traffsys.Repository.inspectionMasterInterface;
import com.traffsys.Repository.lotBatchInterface;
import com.traffsys.Repository.userRepo;
import com.traffsys.entity.InspectionMaster;
import com.traffsys.entity.LotBatchMaster;
import com.traffsys.entity.QCResultMaster;
import com.traffsys.entity.defectMaster;
import com.traffsys.entity.user;

@Service
public class QCResultMasterServices {
	
	@Autowired
	private QCResultDtoInterface dtoInterface;
	@Autowired
	private defectRepository defectRepository;
	
	@Autowired
	private inspectionMasterInterface inspectionMasterInterface;
	
	@Autowired
	private lotBatchInterface batchInterface;
	@Autowired
	private userRepo userrepository;
	

	public Object addQulaity(QCResultDto dto) 
	{
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		String username= auth.getName();
		Map<String,Object> response=new HashMap<>();
		
		System.out.println("DTO = " + dto);
		System.out.println("LotBatchId = " + dto.getLotBatchId());
		System.out.println("InspectionId = " + dto.getInspectionId());
		System.out.println("DefectId = " + dto.getDefectId());
		System.out.println("ResultStatus = " + dto.getResultStatus());
		
		
		InspectionMaster inspect = inspectionMasterInterface.findById(dto.getInspectionId())
				.orElseThrow(()-> new RuntimeException("Inspection Id Not Found"));
		
		LotBatchMaster lotbatch= batchInterface.findById(dto.getLotBatchId())
				.orElseThrow(()-> new RuntimeException("Lot Batch Id Not Found"));
		
		if (dtoInterface.findByLotBatchAndInspection(lotbatch, inspect).isPresent()) {
		    throw new RuntimeException("QC Result already exists for this Lot Batch and Inspection");
		}
		 QCResultMaster qmaster= new QCResultMaster();
		 
		 defectMaster df = null;
		 
		 if ("PASS".equalsIgnoreCase(dto.getResultStatus())) {

			    if (dto.getDefectId() != null) {
			        throw new RuntimeException("Defect should not be provided for PASS result");
			    }

			    qmaster.setDefect(null);
			    qmaster.setDefectQuantity(0);
			}else if ("FAIL".equalsIgnoreCase(dto.getResultStatus())) {

			    if (dto.getDefectId() == null) {
			        throw new RuntimeException("Defect Id is mandatory for FAIL result");
			    }

			    if (dto.getDefectQuantity() == null || dto.getDefectQuantity() <= 0) {
			        throw new RuntimeException("Defect Quantity should be greater than 0");
			    }

			    df = defectRepository.findById(dto.getDefectId())
			            .orElseThrow(() -> new RuntimeException("Defect Id Not Found"));

			    qmaster.setDefect(df);
			    qmaster.setDefectQuantity(dto.getDefectQuantity());

			} else {
			    throw new RuntimeException("Result Status must be PASS or FAIL");
			}
		 
		qmaster.setCreatedBy(username);
		qmaster.setCreatedDate(LocalDateTime.now());
		qmaster.setInspection(inspect);
		
		qmaster.setRemarks(dto.getRemarks());
		qmaster.setResultStatus(dto.getResultStatus().toUpperCase());
		qmaster.setLotBatch(lotbatch);
		qmaster.setStatus(dto.getStatus());
		
		dtoInterface.save(qmaster);
		response.put("status", 200);
		response.put("message", "Add Quality Result Data Successfully");
		
		return response;
	}


	public Page<QCResultDto> allData(int page, int size) 
	{
        Map<String, Object> response= new HashMap<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

	     user usernameforauth= userrepository.findByUsername(username);
	     PageRequest pageable = PageRequest.of(page, size);
	     Page<QCResultMaster> all = dtoInterface.findAll(pageable);
	    
		 return all.map(QCResultMaster ->{
			 QCResultDto dt= new QCResultDto();
			 dt.setDefectId(QCResultMaster.getId());
			 dt.setDefectQuantity(QCResultMaster.getDefectQuantity());
		     dt.setRemarks(QCResultMaster.getRemarks());
		     dt.setInspectionId(QCResultMaster.getInspection().getId());
		     dt.setDefectId(QCResultMaster.getId());
		     dt.setStatus(QCResultMaster.getStatus());
		     dt.setResultStatus(QCResultMaster.getResultStatus());
		     dt.setLotBatchId(QCResultMaster.getLotBatch().getId());
		     dt.setId(QCResultMaster.getId());
		     return dt;
		 });
	}


	public Object updateRowData(Long id, QCResultDto dto) 
	{
        Map<String, Object> response= new HashMap<>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		QCResultMaster qid= dtoInterface.findById(id).orElseThrow(()-> new RuntimeException("quality id Not Found"));
		
		qid.setDefectQuantity(dto.getDefectQuantity());
		qid.setRemarks(dto.getRemarks());
		qid.setResultStatus(dto.getResultStatus());
		qid.setUpdatedDate(LocalDateTime.now());
		qid.setUpdatedBy(username);
		qid.setStatus(dto.getStatus());
		dtoInterface.save(qid);
		
		
		response.put("status", 200);
		response.put("message","updated SuccessFully");
		
		return response;
	}


	public Object deleteRow(Long id)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		QCResultMaster qid= dtoInterface.findById(id).orElseThrow(()-> new RuntimeException("quality id Not Found"));
		dtoInterface.delete(qid);
		
		return "Deleted SuccessFully ";
	}

}
