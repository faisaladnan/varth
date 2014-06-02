package com.central.varth.resp.type;


public class BulkString implements RespType
{
	private int size;
	private String data;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getString() {
		return data;
	}
	public void setString(String string) {
		this.data = string;
	}
}
