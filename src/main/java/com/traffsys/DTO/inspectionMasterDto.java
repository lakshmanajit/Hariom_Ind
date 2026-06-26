package com.traffsys.DTO;

import com.traffsys.entity.Product;
import com.traffsys.entity.StageMaster;

public class inspectionMasterDto 
{
	    private Long id;

	    private String inspectionCode;

	    private String inspectionName;

	    private Product mProduct;

	    private StageMaster mStage;

	    private Integer status;

	    private String description;

		public Long getId() {
			return id;
		}

		public String getInspectionCode() {
			return inspectionCode;
		}

		public String getInspectionName() {
			return inspectionName;
		}

		

		public Product getmProduct() {
			return mProduct;
		}

		public void setmProduct(Product mProduct) {
			this.mProduct = mProduct;
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

		public void setInspectionCode(String inspectionCode) {
			this.inspectionCode = inspectionCode;
		}

		public void setInspectionName(String inspectionName) {
			this.inspectionName = inspectionName;
		}

		

		

		public StageMaster getmStage() {
			return mStage;
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

}
