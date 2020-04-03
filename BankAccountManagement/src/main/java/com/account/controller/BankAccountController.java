package com.account.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.exception.RecordsNotFoundException;
import com.account.model.Customer;
import com.account.model.TransactionMaster;
import com.account.request.TransactionMasterrequest;
import com.account.service.BankAccountService;

@RestController
@RequestMapping("/bankaccount/api")
public class BankAccountController {

	@Autowired
	BankAccountService bankservice;
	
	
	@GetMapping("/{acno}/{transdate}")
	public ResponseEntity<List<TransactionMaster>> getMiniStatement(@PathVariable("acno") long acno,@PathVariable("transdate") String transdate) throws ParseException{
		
		List<TransactionMaster> statement=bankservice.getMiniStatement(acno,transdate);
		
		return new ResponseEntity<List<TransactionMaster>>(statement,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@PostMapping("/createorupdateCustomer")
	public ResponseEntity<?> CreateOrUpdateCustomer(Customer customer) throws RecordsNotFoundException{
		Customer updatecustomer=bankservice.CreateOrUpdateCustomer(customer);
		return new ResponseEntity<Customer>(updatecustomer,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/fundtransfer")
	public ResponseEntity<?> fundTransfer(TransactionMasterrequest transrequest) throws RecordsNotFoundException, ParseException {
		TransactionMaster fundtransfer=bankservice.fundTransfer(transrequest);
		return new ResponseEntity<TransactionMaster>(fundtransfer,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	
	
	
	
}
