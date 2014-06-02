package com.central.varth.resp.type;

import java.util.ArrayList;
import java.util.List;

public class RespArray implements RespType {
	private int size;
	private List<RespType> element = new ArrayList<RespType>();

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<RespType> getElement() {
		return element;
	}

	public void setElement(List<RespType> element) {
		this.element = element;
	}

}
