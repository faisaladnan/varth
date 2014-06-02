package com.central.varth.resp.type;


public class SimpleString implements RespType 
{
	private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
