package com.zocom.assignment.db;

import java.io.IOException;

import com.zocom.assignment.model.Account;
import com.zocom.assignment.model.ServiceResponse;

public class AsyncDBResponse<E> {
  
  public interface Success<E> {
    void proceed(E element) throws IOException;
  }
  
  public interface Failure {
    void proceed(String message) throws IOException;
  }
  
  private Success<E> mSuccessHandler;
  private Failure mFailureHandler;
  
  public AsyncDBResponse<E> success(Success<E> handler) {
    mSuccessHandler = handler;
    return this;
  }
  
  public AsyncDBResponse<E> failure(Failure handler) {
    mFailureHandler = handler;
    return this;
  }
  
  public void invoke(ServiceResponse<E> response) throws IOException {
    if(response.isSuccessful()) {
      mSuccessHandler.proceed(response.getElement());
    }
    else {
      mFailureHandler.proceed(response.getErrorMessage());
    }
  }
}
