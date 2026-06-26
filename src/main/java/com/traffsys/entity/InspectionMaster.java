package com.traffsys.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "inspection_master")
public class InspectionMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inspection_code", nullable = false, unique = true)
    private String inspectionCode;

    @Column(name = "inspection_name", nullable = false)
    private String inspectionName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product mProduct;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private StageMaster mStage;

    private Integer status;

    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

	public Long getId() {
		return id;
	}

	public String getInspectionCode() {
		return inspectionCode;
	}

	public String getInspectionName() {
		return inspectionName;
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

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}

	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}

	

	

	public Product getmProduct() {
		return mProduct;
	}

	public StageMaster getmStage() {
		return mStage;
	}

	public void setmProduct(Product mProduct) {
		this.mProduct = mProduct;
	}

	public void setmStage(StageMaster mStage) {
		this.mStage = mStage;
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