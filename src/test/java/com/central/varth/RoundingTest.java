package com.central.varth;

import java.math.BigDecimal;

import org.junit.Test;

public class RoundingTest {

	@Test	
	public void roundTest()
	{
		String doubleVal = "1.743";
		String doubleVal1 = "0.249";
		BigDecimal bdTest = new BigDecimal(  doubleVal);
		BigDecimal bdTest1 = new BigDecimal(  doubleVal1 );
		bdTest = bdTest.setScale(2, BigDecimal.ROUND_HALF_UP);
		bdTest1 = bdTest1.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("bdTest:"+bdTest); //1.75
		System.out.println("bdTest1:"+bdTest1);//0.75, no problem
	}
}
