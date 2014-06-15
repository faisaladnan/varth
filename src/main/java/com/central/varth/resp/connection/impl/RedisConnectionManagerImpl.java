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

package com.central.varth.resp.connection.impl;

import java.util.ArrayList;
import java.util.List;

import com.central.varth.resp.cluster.ClusterNode;
import com.central.varth.resp.connection.ConnectionManager;
import com.central.varth.resp.connection.RespClient;
import com.central.varth.resp.connection.RespClientBuilder;

public class RedisConnectionManagerImpl implements ConnectionManager<ClusterNode> 
{

	private RespClientBuilder<ClusterNode> respClientBuilder;
	
	@Override
	public List<RespClient> buildAllClient(List<ClusterNode> nodes) {
		List<RespClient> clients = new ArrayList<RespClient>();
		for (ClusterNode node:nodes)
		{
			RespClient client = buildClient(node);
			clients.add(client);
		}
		return clients;
	}

	@Override
	public RespClient buildClient(ClusterNode node) {
		RespClient client = respClientBuilder.build(node);	
		return client;
	}

	@Override
	public Integer findSlot(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RespClient findClient(Integer slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRespClientBuilder(
			RespClientBuilder<ClusterNode> builder) {
		this.respClientBuilder = builder;		
	}
}
