package com.traffsys.DTO;

public class stageDto 
{
	    private Long id;

	    private String stageCode;

	    private String stageName;

	    private Integer status;

	    private String description;

	    private String createdBy;

		public Long getId() {
			return id;
		}

		public String getStageCode() {
			return stageCode;
		}

		public String getStageName() {
			return stageName;
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

		public void setId(Long id) {
			this.id = id;
		}

		public void setStageCode(String stageCode) {
			this.stageCode = stageCode;
		}

		public void setStageName(String stageName) {
			this.stageName = stageName;
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

}
