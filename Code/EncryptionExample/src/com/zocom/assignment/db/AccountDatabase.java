package com.zocom.assignment.db;

import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.ServiceResponse;

public class AccountDatabase {	
	private AccountQuery mAccountQuery;
	
	public AccountDatabase() {
		mAccountQuery = new AccountQuery();
	}
	
	public ServiceResponse<Account> get(String username) {
		return mAccountQuery.executeFind(username);
	}
	
	public ServiceResponse<Account> insert(Account account) {
		return mAccountQuery.executeInsert(account);
	}
	
	public ServiceResponse<Account> delete(Account account) {
		return mAccountQuery.executeDelete(account);
	}
}
