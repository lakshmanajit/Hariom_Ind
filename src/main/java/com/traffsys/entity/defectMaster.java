package com.traffsys.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class defectMaster
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="defect_code", nullable = false, unique = true)
	private String defectCode;
	@Column(name="defect_name",nullable= false)
	private String defectName;
	private Integer status;
	private String description;

	@Column(name="created_By")
	private String createdBy;
	@Column(name="created_date")
	private LocalDateTime createdDate;

	@Column(name="updated_By")
	private String updatedBy;
	@Column(name="updated_date")
	private LocalDateTime updatedDate;
	public Long getId() {
		return id;
	}
	public String getDefectCode() {
		return defectCode;
	}
	public String getDefectName() {
		return defectName;
	}
	public Integer getStatus() {
		return status;
	}
	public String getDescription() {
		return description;
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
	public void setDefectCode(String defectCode) {
		this.defectCode = defectCode;
	}
	public void setDefectName(String defectName) {
		this.defectName = defectName;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setDescription(String description) {
		this.description = description;
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
