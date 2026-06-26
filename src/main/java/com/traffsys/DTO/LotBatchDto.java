package com.traffsys.DTO;

import java.time.LocalDate;

import com.traffsys.entity.Product;

public class LotBatchDto {

    private Long id;

    private String lotNumber;

    

    private Product mProduct;

    private Integer quantity;

    private LocalDate manufacturingDate;

    private LocalDate expiryDate;

    private Integer status;

    private String description;

	public Long getId() {
		return id;
	}

	public String getLotNumber() {
		return lotNumber;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	
	

	public Product getmProduct() {
		return mProduct;
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

    // Getters and Setters
}