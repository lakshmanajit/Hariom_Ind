package com.traffsys.Authservices;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffsys.Repository.MasterPrivilegeRepo;
import com.traffsys.entity.MasterPrivilege;

@Service
public class MasterPrivilege_Service {

	@Autowired
	private MasterPrivilegeRepo privilege_repo;
	
	public Object addPrivilge(MasterPrivilege masterPrivilege)
	{
		MasterPrivilege exitingCode = privilege_repo.findByPrivilegeName(masterPrivilege.getPrivilegeName());
		
		if (exitingCode != null) {
            throw new RuntimeException(
                    "Privilege already exists"
            );
        }
		
		privilege_repo.save(masterPrivilege);
		Map<String, Object> response = new HashMap<>();
		    response.put("status", 200);
	        response.put("message", "Role Added Successfully");
	        response.put("data", masterPrivilege);
		return response;
	}

}
