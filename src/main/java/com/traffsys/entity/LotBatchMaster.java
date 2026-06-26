package com.traffsys.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "lot_batch_master")
public class LotBatchMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_number", nullable = false, unique = true)
    private String lotNumber;

   
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product mProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "description")
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

	public String getLotNumber() {
		return lotNumber;
	}

	
	public Product getmProduct() {
		return mProduct;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
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

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	

	public void setmProduct(Product mProduct) {
		this.mProduct = mProduct;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
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

    // Getters and Setters
}