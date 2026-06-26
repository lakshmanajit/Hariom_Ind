package com.traffsys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traffsys.entity.StageMaster;

public interface  stageRepo extends JpaRepository<StageMaster, Long> {
	 StageMaster findByStageCode(String stageCode);

}
