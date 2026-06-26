package com.traffsys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traffsys.entity.defectMaster;
@Repository
public interface defectRepository extends JpaRepository<defectMaster, Long> {

	defectMaster findBydefectCode(String defectCode);

	defectMaster findByDefectName(String username);

}
