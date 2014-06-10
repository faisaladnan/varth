/**
 * 
 * Copyright 2014 Central Software

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

package com.central.varth.resp.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.central.varth.resp.RespClient;
import com.central.varth.resp.RespDeserializer;
import com.central.varth.resp.RespException;
import com.central.varth.resp.type.RespType;

public class DefaultRespClientImpl implements RespClient {

	public static final int DEFAULT_CONNECT_TIMEOUT = 2000;
	public static final int DEFAULT_SOCKET_TIMEOUT = 2000;
	public static final boolean DEFAULT_AUTOCONNECT = true;
	
	private String hostname;
	private int port;
	private int connectTimeout;
	private int socketTimeout;
	private Socket socket = new Socket();
	private OutputStream out;
	private InputStream in;
	
	
	public DefaultRespClientImpl(String hostname, int port) throws IOException
	{
		this(hostname, port, DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT, DEFAULT_AUTOCONNECT);		
	}
	
	public DefaultRespClientImpl(String hostname, int port, int connectTimout, int socketTimeout, boolean autoConnect) throws IOException
	{		
		this.setHostname(hostname);
		this.setPort(port);
		socket.setReuseAddress(true);
		socket.setKeepAlive(true); // Will monitor the TCP connection is valid
		socket.setTcpNoDelay(true); // Socket buffer Whetherclosed, to ensure timely delivery of data
		socket.setSoLinger(true, 0); // Control calls close () method, the underlying socket is closed immediately
		socket.setSoTimeout(socketTimeout);
		if (autoConnect)
		{
			socket.connect(new InetSocketAddress(hostname, port), connectTimout);
		}
		this.out = socket.getOutputStream();
		this.in = socket.getInputStream();	
		this.setConnectTimeout(connectTimout);
		this.setSocketTimeout(socketTimeout);
	}	
	
	@Override
	public void connect(String hostname, int port) throws IOException {
		if (!socket.isClosed())
		{
			socket.connect(new InetSocketAddress(hostname, port), this.getConnectTimeout());
		}			
	}

	@Override
	public <T extends RespType> T send(String command, Class<T> responseClass) throws IOException, RespException {
		out.write(command.getBytes());
		socket.getOutputStream().flush();
		RespDeserializer deserializer = new RespDeserializer(in);
		T response = deserializer.deserialize(responseClass);
		return response;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

}
