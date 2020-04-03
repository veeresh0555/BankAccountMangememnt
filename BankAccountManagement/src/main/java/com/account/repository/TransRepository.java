package com.account.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.account.model.TransactionMaster;


@Repository
public interface TransRepository extends JpaRepository<TransactionMaster, Long> {

	@Query("select tmm from TransactionMaster tmm where tmm.frmaccount=:frmaccount and tmm.transdate=:transdate")
	public List<TransactionMaster> getTransDataByacNoAndDate(@Param("frmaccount")long frmaccount,@Param("transdate") Date transdate);

}
