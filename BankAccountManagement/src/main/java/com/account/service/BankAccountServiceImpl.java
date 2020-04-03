package com.account.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.exception.RecordsNotFoundException;
import com.account.model.AccountNumberEntity;
import com.account.model.Customer;
import com.account.model.TransactionMaster;
import com.account.repository.AccountNumberRepository;
import com.account.repository.BankAccountRepository;
import com.account.repository.TransRepository;
import com.account.request.TransactionMasterrequest;


@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	BankAccountRepository customerRepository;
	
	@Autowired
	AccountNumberRepository accRepository;
	
	@Autowired
	TransRepository transRepository;
	
	
	@Override
	public Customer CreateOrUpdateCustomer(Customer customer) throws RecordsNotFoundException {
		Optional<Customer> custById=customerRepository.findById(customer.getCustid());
		System.out.println("Customer availability check:  "+(custById.isPresent()));
		if (custById.isPresent()) {
			Customer newcustomer=custById.get();
			newcustomer.setFirstname(customer.getFirstname());
			newcustomer.setLastname(customer.getLastname());
			newcustomer.setEmail(customer.getEmail());
			newcustomer.setMobile(customer.getMobile());
			newcustomer=customerRepository.save(newcustomer);
			return newcustomer;
		}else {
			AccountNumberEntity ac=new AccountNumberEntity();
			//ac.setCust(customer.getCustid());
			  ac.setAcno(generateaccountNumber()); 
			  ac.setOpeningbalance(5000.00);
			  customer=customerRepository.save(customer);
			return customer;
		}
		
	}
	@Override
	public TransactionMaster fundTransfer(TransactionMasterrequest transreq) throws RecordsNotFoundException, ParseException {

		Optional<AccountNumberEntity> fromac=accRepository.findByAccNumber(transreq.getFrmaccount());
		if(fromac.isPresent()) {
			Optional<AccountNumberEntity> toac=accRepository.findByAccNumber(transreq.getToaccount());
			if(toac.isPresent()) {
				AccountNumberEntity updatefrmamount=fromac.get();
				AccountNumberEntity updateToamount=toac.get();
				
				if(updatefrmamount.getOpeningbalance()>=transreq.getAmount()) {
					updatefrmamount.setOpeningbalance((updatefrmamount.getOpeningbalance()-transreq.getAmount()));
					accRepository.save(updatefrmamount);
					
					updateToamount.setOpeningbalance((updateToamount.getOpeningbalance()+transreq.getAmount()));
					
					accRepository.save(updateToamount);
					
					TransactionMaster transmaster=new TransactionMaster();
					transmaster.setTransid(transreq.getTransid());
					transmaster.setAmount(transreq.getAmount());
					transmaster.setFrmaccount(transreq.getFrmaccount());
					transmaster.setToaccount(transreq.getToaccount());
					
					String sDate1=transreq.getTransdate();
					java.util.Date sdate = null;
					sdate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
					transmaster.setTransdate(sdate);
					transmaster.setRemarks(transreq.getRemarks());
					transmaster=transRepository.save(transmaster);
					return transmaster;
				}else {
					throw new RecordsNotFoundException("Insufficiant Amount !!!!");
				}
				
			}else {
				throw new RecordsNotFoundException("To Account Must be Register accountNumber Only!!");
			}
			}else {
			throw new RecordsNotFoundException("From Account Number Not Found");
		}
		
	}	
	@Override
	public List<TransactionMaster> getMiniStatement() {
		List<TransactionMaster> minstatement=transRepository.findAll();
		if(minstatement.size()>0) {
			return minstatement;
		}else {
			return new ArrayList<TransactionMaster>();//it will return new Empty List
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static long generateaccountNumber() {//int length
	    Random random = new Random();
	    char[] digits = new char[12];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < 12; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    
	    
	    System.out.println("Digits: "+Long.parseLong(new String(digits)));
	    return Long.parseLong(new String(digits));
	}



	
}
