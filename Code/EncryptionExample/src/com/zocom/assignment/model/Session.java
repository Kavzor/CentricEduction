package com.zocom.assignment.model;

public class Session<T> {
  
  public static final int REDIRECT_HOME = 0;
  
  private T mType;
  
  public void setSessionOn(T type) {
    mType = type;
  }
  
  public boolean isValid() {
    return mType != null;
  }
  
  public void trashSession() {
    mType = null;
  }
  
  public T getSessionType() {
    return mType;
  }
}
