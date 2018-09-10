package com.zocom.assignment.service;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.zocom.assignment.db.AccountDatabase;
import com.zocom.assignment.db.AsyncDBResponse;
import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.ServiceResponse;

public class AccountService implements AccountDao {
	
	private AccountDatabase mDatabase;
	
	public AccountService() {
		mDatabase = new AccountDatabase();
	}

	@Override
  public ServiceResponse<Account> fetch(String username) {
    return mDatabase.get(username);
  }
	
	@Override
	public void fetch(String username, AsyncDBResponse<Account> responseHandler) throws IOException{
	  responseHandler.invoke(fetch(username));
	}
	
	@Override
	public ServiceResponse<Account> login(String username, String password) {
		ServiceResponse<Account> response = fetch(username);
		if(response.isSuccessful() && !BCrypt.checkpw(password, response.getElement().getPassword())) {
			response.setSuccessful(false);
			response.setErrorMessage("Invalid credentials");
		}
		return response;
	}
	
	@Override
	public void login(String username, String password, AsyncDBResponse<Account> responseHandler) throws IOException {
	  responseHandler.invoke(login(username, password));
	}

	@Override
	public ServiceResponse<Account> register(Account account) {
		account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
		return mDatabase.insert(account);
	}
	
	@Override
  public void register(Account account, AsyncDBResponse<Account> responseHandler) throws IOException {
	  responseHandler.invoke(register(account));
  }

	@Override
	public ServiceResponse<Account> remove(String username) {
		return mDatabase.delete(fetch(username).getElement());
	}

  @Override
  public void remove(String username, AsyncDBResponse<Account> responseHandler) throws IOException {
    responseHandler.invoke(remove(username));
  }	
}
