package com.account.service;

import java.text.ParseException;
import java.util.List;

import com.account.exception.RecordsNotFoundException;
import com.account.model.Customer;
import com.account.model.TransactionMaster;
import com.account.request.TransactionMasterrequest;

public interface BankAccountService {

	public Customer CreateOrUpdateCustomer(Customer customer) throws RecordsNotFoundException;

	public TransactionMaster fundTransfer(TransactionMasterrequest transrequest) throws RecordsNotFoundException,ParseException;

	public List<TransactionMaster> getMiniStatement();

	

}
