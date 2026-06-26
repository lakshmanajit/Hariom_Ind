package com.traffsys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traffsys.entity.MasterPrivilege;
@Repository
public interface MasterPrivilegeRepo extends JpaRepository<MasterPrivilege, Long>{

	MasterPrivilege findByPrivilegeName(String privilegeName);

}
