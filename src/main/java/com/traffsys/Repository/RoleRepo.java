package com.traffsys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traffsys.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByRoleName(String roleName);

}
