package com.traffsys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traffsys.entity.InspectionMaster;

@Repository
public interface inspectionMasterInterface extends JpaRepository<InspectionMaster, Long> {

	InspectionMaster findByInspectionCode(String inspectionCode);

}
