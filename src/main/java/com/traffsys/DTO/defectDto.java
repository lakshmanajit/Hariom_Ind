package com.traffsys.DTO;

public class defectDto 
{
	private Long id;
    private String defectCode;
    private String defectName;
    private Integer status;
    private String description;
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

}
