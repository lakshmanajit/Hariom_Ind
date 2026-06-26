package com.traffsys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traffsys.entity.LotBatchMaster;
@Repository
public interface lotBatchInterface extends JpaRepository<LotBatchMaster, Long>{

	LotBatchMaster findByLotNumber(String lotNumber);

	


}
