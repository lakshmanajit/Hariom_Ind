package com.traffsys.DTO;

import java.util.List;

public class RolePrivilegeAssignDto 
{
	private long roleId;
	private List<Long> privilegeIds;
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public List<Long> getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(List<Long> privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
	

}
