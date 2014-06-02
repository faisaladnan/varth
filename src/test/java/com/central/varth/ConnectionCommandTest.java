package com.central.varth;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectionCommandTest {

	public String hostname = "localhost";
	public int port = 7001;
	public Socket redisSocket;
	public PrintWriter out;
	public BufferedReader in;

	@Before
	public void init()
	{

		try {
			redisSocket = new Socket(hostname, port);
			out = new PrintWriter(redisSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(redisSocket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("initialized");
	}
	
	@Test
	public void echoTest()
	{
		String command = "echo Hello";
		out.println(command);
		try {
			InputStream ins = redisSocket.getInputStream();
			int i = 0;
			int prevByte = 0;
			while ((i = ins.read()) > 0)
			{
				System.err.println(i + " " + new String(Character.toChars(i)));
				if (prevByte == 13 && i == 10)
				{
					//break;
				}
				prevByte = i;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void msetCommandTest()
	{
		String command = "*5\r\n$4\r\nMSET\r\n$3\r\nONE\r\n$1\r\n1\r\n$3\r\nTWO\r\n$12\r\n";
		out.println(command);
		try {
			int data = in.read();
			while (data != -1)
			{
				char theChar = (char) data;
				System.err.println(theChar);
				data = in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void lrangeCommandTest()
	{
		String command = "*4\r\n$6\r\nLRANGE\r\n$6\r\nmylist\r\n$1\r\n0\r\n$1\r\n1\r\n";
		out.println(command);
		try {
			String data = in.readLine();
			while (data != null)
			{
				System.err.println(data);
				data = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void lpopCommandTest()
	{
		String command = "*2\r\n$4\r\nLPOP\r\n$5\r\nmylist\r\n";
		out.println(command);
		try {
			String data = in.readLine();
			while (data != null)
			{
				System.err.println(data);
				data = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void setCommandTest()
	{
		String command = "*3\r\n$3\r\nSET\r\n$5\r\nmykey\r\n$5\r\nhello\r\n";
		out.println(command);
		try {
			String data = in.readLine();
			while (data != null)
			{
				System.err.println(data);
				data = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void pingCommandTest()
	{
		String command = "*1\r\n$4\r\nPING\r\n";
		out.println(command);
		try {
			String data = in.readLine();
			while (data != null)
			{
				System.err.println(data);
				data = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCommandTest()
	{
		String command = "*2\r\n$3\r\nGET\r\n$3\r\ntwo\r\n";
		out.println(command);
		try {
			String data = in.readLine();
			while (data != null)
			{
				System.err.println(data);
				data = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		

	@After
	public void tearDown()
	{
		try {
			redisSocket.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("bubar");
	}
}
