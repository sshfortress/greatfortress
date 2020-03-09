package com.sshfortress.common.exception;

public class CustomException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  public String message;
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public CustomException(String message)
  {
    this.message = message;
  }
}
