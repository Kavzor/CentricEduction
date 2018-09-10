package com.zocom.assignment.db;

import java.io.Closeable;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.ServiceResponse;

public class AccountQuery {
	private static final String FIND_QUERY = "SELECT * FROM account_table WHERE username=?";
	private static final String INSERT_QUERY = "INSERT INTO account_table (email, username, password) VALUES (?,?,?)";
	private static final String DELETE_QUERY = "DELETE FROM account_table WHERE username=?";
	private static final int QUERY_NO_UPDATE = 0;
	
	public ServiceResponse<Account> executeFind(String username) {
		ServiceResponse<Account> response = new ServiceResponse<>();
		Database database = Database.getInstance();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = database.prepare(FIND_QUERY);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Account account = new Account();
				account.setEmail(rs.getString("email"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				
				response.setElement(account);
			}
			else {
				response.setErrorMessage("User could not be found");
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			close(rs, stmt);
		}
		return response;
	}
	
	public ServiceResponse<Account> executeInsert(Account account) {
		ServiceResponse<Account> response = new ServiceResponse<>();
		Database database = Database.getInstance();
		PreparedStatement stmt = null;
		
		try {
			stmt = database.prepare(INSERT_QUERY);
			stmt.setString(1, account.getEmail());
			stmt.setString(2, account.getUsername());
			stmt.setString(3, account.getPassword());
			
			if(stmt.executeUpdate() == QUERY_NO_UPDATE) {
				response.setErrorMessage("Server is busy, try again later");
			}
			else {
				response.setElement(account);
			}
		}
		catch(SQLIntegrityConstraintViolationException ex) {
			response.setErrorMessage("Username already taken");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			close(stmt);
		}
		return response;
	}
	
	public ServiceResponse<Account> executeDelete(Account account) {
		ServiceResponse<Account> response = new ServiceResponse<>();
		Database database = Database.getInstance();
		PreparedStatement stmt = null;
		
		try {
			stmt = database.prepare(DELETE_QUERY);
			stmt.setString(1, account.getUsername());
			
			if(stmt.executeUpdate() == QUERY_NO_UPDATE) {
				response.setErrorMessage("Server is busy, try again later");
			}
			else {
				response.setSuccessful(true);
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return response;
	}
	
	private void close(AutoCloseable...closeables) {
		for(AutoCloseable closeable: closeables) {
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
