/**
 * 
 * Copyright ${year} Central Software

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.central.varth.resp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import redis.clients.util.RedisInputStream;

import com.central.varth.resp.type.BulkString;
import com.central.varth.resp.type.RespArray;
import com.central.varth.resp.type.RespInteger;
import com.central.varth.resp.type.RespType;
import com.central.varth.resp.type.SimpleString;

public class RespDeserializerTest {

	@Test
	public void deserializeSimpleString() throws IOException, RespException
	{
		String str = "+OK\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		SimpleString type = deserializer.deserialize(SimpleString.class);
		System.err.println(type.getString());
		Assert.assertEquals("OK", type.getString());
		Assert.assertNotNull(type);
	}
	
	@Test
	public void deserializeSimpleStringWithJedis() throws IOException, RespException
	{
		String str = "+OK\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RedisInputStream ris = new RedisInputStream(is);
		RespDeserializer deserializer = new RespDeserializer(ris);
		SimpleString type = deserializer.deserialize(SimpleString.class);
		System.err.println(type.getString());
		Assert.assertEquals("OK", type.getString());
		Assert.assertNotNull(type);
	}	
	
	@Test
	public void deserializeRespInteger() throws IOException, RespException
	{
		String str = ":1000\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespInteger type = deserializer.deserialize(RespInteger.class);
		System.err.println(type.getInteger());
		Assert.assertEquals("1000", type.getInteger());
		Assert.assertNotNull(type);
	}	
	
	@Test
	public void deserializeBulkString() throws IOException, RespException
	{
		String str = "$6\r\nfoobar\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		BulkString type = deserializer.deserialize(BulkString.class);
		System.err.println(type.getSize());
		System.err.println(type.getString());
		Assert.assertEquals(6, type.getSize());		
		Assert.assertEquals("foobar", type.getString());				
		Assert.assertNotNull(type);
	}
	
	@Test
	public void deserializeBulkStringWithJedis() throws IOException, RespException
	{
		String str = "$6\r\nfoobar\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RedisInputStream ris = new RedisInputStream(is);
		RespDeserializer deserializer = new RespDeserializer(ris);
		BulkString type = deserializer.deserialize(BulkString.class);
		System.err.println(type.getSize());
		System.err.println(type.getString());
		Assert.assertEquals(6, type.getSize());		
		Assert.assertEquals("foobar", type.getString());				
		Assert.assertNotNull(type);
	}	
	
	@Test
	public void deserializeRespArray() throws IOException, RespException
	{
		String str = "*3\r\n$3\r\nfoo\r\n$3\r\nbar\r\n:4001\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespArray type = deserializer.deserialize(RespArray.class);
		System.err.println(type.getSize());
		System.err.println(type.toString());
		Assert.assertEquals(3, type.getSize());
		Assert.assertEquals(3, type.getElement().size());
		for (int i=0;i<type.getSize();i++)
		{
			RespType t = type.getElement().get(i);
			System.err.println(t.toString());
		}
		Assert.assertNotNull(type);
	}	
	
	@Test
	public void deserializeRespArrayWithJedis() throws IOException, RespException
	{
		String str = "*3\r\n$3\r\nfoo\r\n$3\r\nbar\r\n:4001\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RedisInputStream ris = new RedisInputStream(is);
		RespDeserializer deserializer = new RespDeserializer(ris);
		RespArray type = deserializer.deserialize(RespArray.class);
		System.err.println(type.getSize());
		System.err.println(type.toString());
		Assert.assertEquals(3, type.getSize());
		Assert.assertEquals(3, type.getElement().size());
		for (int i=0;i<type.getSize();i++)
		{
			RespType t = type.getElement().get(i);
			System.err.println(t.toString());
		}
		Assert.assertNotNull(type);
	}
	
	@Test
	public void deserializeRespArrayWithJedisSmallBufferSize() throws IOException, RespException
	{
		String str = "*3\r\n$3\r\nfoo\r\n$3\r\nbar\r\n:4001\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RedisInputStream ris = new RedisInputStream(is);
		RespDeserializer deserializer = new RespDeserializer(ris, 8);
		RespArray type = deserializer.deserialize(RespArray.class);
		System.err.println(type.getSize());
		System.err.println(type.toString());
		Assert.assertEquals(3, type.getSize());
		Assert.assertEquals(3, type.getElement().size());
		for (int i=0;i<type.getSize();i++)
		{
			RespType t = type.getElement().get(i);
			System.err.println(t.toString());
		}
		Assert.assertNotNull(type);
	}	
	
	@Test
	public void deserializeEmptyRespArray() throws IOException, RespException
	{
		String str = "*0\r\n";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		RespDeserializer deserializer = new RespDeserializer(is);
		RespArray type = deserializer.deserialize(RespArray.class);
		Assert.assertNotNull(type);
		System.err.println(type.getSize());
		Assert.assertEquals(0, type.getSize());
		Assert.assertEquals(0, type.getElement().size());
		for (int i=0;i<type.getElement().size();i++)
		{
			RespType t = type.getElement().get(i);
			System.err.println(t.toString());
		}		
	}				
}
