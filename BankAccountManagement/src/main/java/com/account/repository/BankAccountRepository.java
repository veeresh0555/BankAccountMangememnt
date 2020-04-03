package com.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.model.Customer;


@Repository
public interface BankAccountRepository extends JpaRepository<Customer, Long> {

}
