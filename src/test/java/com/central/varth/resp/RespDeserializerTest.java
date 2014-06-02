package com.central.varth.resp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.central.varth.resp.type.RespType;

public class RespDeserializerTest {

	@Test
	public void deserializeSimpleString() throws IOException, RespException
	{
		String str = "+OK\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespType type = deserializer.deserialize();
		Assert.assertNotNull(type);
	}
	
	@Test
	public void deserializeRespInteger() throws IOException, RespException
	{
		String str = ":1000\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespType type = deserializer.deserialize();
		Assert.assertNotNull(type);
	}	
	
	@Test
	public void deserializeBulkString() throws IOException, RespException
	{
		String str = "$6\r\nfoobar\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespType type = deserializer.deserialize();
		Assert.assertNotNull(type);
	}		
	
	@Test
	public void deserializeRespArray() throws IOException, RespException
	{
		String str = "*3\r\n$3\r\nfoo\r\n$3\r\nbar\r\n:4001\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespType type = deserializer.deserialize();
		Assert.assertNotNull(type);
	}			
	
	@Test
	public void deserializeEmptyRespArray() throws IOException, RespException
	{
		String str = "*0\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespType type = deserializer.deserialize();
		Assert.assertNotNull(type);
	}				
}
