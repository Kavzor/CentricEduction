package com.zocom.assignment.service;

import java.io.IOException;

import javax.servlet.ServletException;

public class SessionHandler {
  
  public interface Handler {
    void handle() throws IOException, ServletException;
  }
  
  private Handler mSuccessHandler;
  private Handler mFailureHandler;

  public SessionHandler success(Handler success) {
    mSuccessHandler = success;
    return this;
  }
  
  public SessionHandler failure(Handler faliure) {
    mFailureHandler = faliure;
    return this;
  }
  
  public Handler getSuccessHandler() {
    return mSuccessHandler;
  }
  
  public Handler getFailureHandler() {
    return mFailureHandler;
  }
  
  public Handler getHandler(boolean isSuccess) {
    return isSuccess ? mSuccessHandler : mFailureHandler;
  }
  
  public static SessionHandler set() {
    return new SessionHandler();
  }
}
