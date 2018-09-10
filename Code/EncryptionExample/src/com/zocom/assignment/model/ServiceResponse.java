package com.zocom.assignment.model;

import com.zocom.assignment.db.AsyncDBResponse;

public class ServiceResponse<E> {
  
  public interface Success<E> {
    void proceed(E element);
  }
  
  public interface Failure {
    void proceed(String errorMessage);
  }
  
	private E element;
	private boolean successful;
	private String errorMessage;
	
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.successful = true;
		this.element = element;
	}
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.successful = false;
		this.errorMessage = errorMessage;
	}
	
	public AsyncDBResponse<E> async() {
	  return new AsyncDBResponse<E>();
	}
}
