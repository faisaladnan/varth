package com.central.varth.resp;

import com.central.varth.resp.type.RespError;

public class RespException extends Exception {

	private static final long serialVersionUID = 1L;
	private RespError error;
	
	public RespException()
	{
		
	}
	
	public RespException(String message)
	{
		super(message);
	}

	public RespException(String message, RespError error)
	{
		super(message);
		this.error = error;
	}
	
	public RespError getError() {
		return error;
	}

	public void setError(RespError error) {
		this.error = error;
	}
}
