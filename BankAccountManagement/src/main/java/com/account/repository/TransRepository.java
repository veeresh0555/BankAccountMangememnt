package com.account.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.account.model.TransactionMaster;


@Repository
public interface TransRepository extends JpaRepository<TransactionMaster, Long> {

}
