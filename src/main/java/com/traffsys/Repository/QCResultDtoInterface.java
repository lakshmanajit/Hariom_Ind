package com.traffsys.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traffsys.entity.InspectionMaster;
import com.traffsys.entity.LotBatchMaster;
import com.traffsys.entity.QCResultMaster;
@Repository
public interface QCResultDtoInterface extends JpaRepository<QCResultMaster, Long>{
	Optional<QCResultMaster> findByLotBatchAndInspection(
	        LotBatchMaster lotBatch,
	        InspectionMaster inspection);
}
