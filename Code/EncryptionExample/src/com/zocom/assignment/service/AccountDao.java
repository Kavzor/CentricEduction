package com.zocom.assignment.service;

import java.io.IOException;

import com.zocom.assignment.db.AsyncDBResponse;
import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.ServiceResponse;

public interface AccountDao {
  ServiceResponse<Account> fetch(String username);
  void fetch(String username, AsyncDBResponse<Account> responseHandler) throws IOException;
	
  ServiceResponse<Account> login(String username, String password);
  void login(String username, String password, AsyncDBResponse<Account> responseHandler) throws IOException;
  
	ServiceResponse<Account> register(Account account);
  void register(Account account, AsyncDBResponse<Account> responseHandler) throws IOException;
  
	ServiceResponse<Account> remove(String username);
  void remove(String username, AsyncDBResponse<Account> responseHandler) throws IOException;
}
