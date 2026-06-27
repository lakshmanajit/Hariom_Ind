package com.traffsys.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="qc_result_master")
public class QCResultMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Lot Batch Master
	@ManyToOne
	@JoinColumn(name="lot_batch_id",nullable=false)
	private LotBatchMaster lotBatch;
	//Inspection master
	@ManyToOne
	@JoinColumn(name="inspection_id",nullable = false)
	private InspectionMaster inspection;
	//pass & Fail
	@Column(name="result_status",nullable = false)
	private String resultStatus;
	//Defect Only If fail
	@ManyToOne
	@JoinColumn(name="defect_id",nullable = true)
	private defectMaster defect;
	
	@Column(name="defect_quantity")
	private Integer defectQuantity;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	public Long getId() {
		return id;
	}

	public LotBatchMaster getLotBatch() {
		return lotBatch;
	}

	public InspectionMaster getInspection() {
		return inspection;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public defectMaster getDefect() {
		return defect;
	}

	public Integer getDefectQuantity() {
		return defectQuantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public Integer getStatus() {
		return status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLotBatch(LotBatchMaster lotBatch) {
		this.lotBatch = lotBatch;
	}

	public void setInspection(InspectionMaster inspection) {
		this.inspection = inspection;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public void setDefect(defectMaster defect) {
		this.defect = defect;
	}

	public void setDefectQuantity(Integer defectQuantity) {
		this.defectQuantity = defectQuantity;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
