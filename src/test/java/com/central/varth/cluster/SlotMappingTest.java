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

package com.central.varth.cluster;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.central.varth.resp.ProtocolConstant;
import com.central.varth.resp.cluster.ClusterNode;
import com.central.varth.resp.cluster.ClusterNodeParser;
import com.central.varth.resp.cluster.DefaultClusterNodeParser;
import com.central.varth.resp.cluster.DefaultSlotMappingService;
import com.central.varth.resp.cluster.SlotMappingService;
import com.central.varth.resp.connection.ConnectionManager;
import com.central.varth.resp.connection.RespClient;
import com.central.varth.resp.connection.RespClientBuilder;
import com.central.varth.resp.connection.impl.RedisConnectionManagerImpl;
import com.central.varth.resp.connection.impl.RedisRespClientImpl;

public class SlotMappingTest {

	private List<RespClient> clients;
	private String rawClusterInfo = "1350e2bb7b20f160e54629958b4dabb772c661b0 127.0.0.1:7002 master - 0 1402694957597 22 connected 0-565 5962-10922 11422-11855\n" + 
			"d8806a2ec150cc0153b4ea16d837e7958f81b4cb 127.0.0.1:7006 slave 35919c478bfd35677d33902badc2508dc51776d0 0 1402694959106 21 connected\n" + 
			"ddebc4aeb5a96b5ceffd0d64c571c3885c2e7ded :0 myself,slave 7ce9cdc2c8dec44a0f09096af6f8f9865257e724 0 0 19 connected\n" + 
			"35919c478bfd35677d33902badc2508dc51776d0 127.0.0.1:7003 master - 0 1402694958605 21 connected 11856-16383\n" + 
			"6ab17726d88c1fdf8693b22b25aef8ba0e806efa 127.0.0.1:7005 slave 1350e2bb7b20f160e54629958b4dabb772c661b0 0 1402694958101 22 connected\n" + 
			"7ce9cdc2c8dec44a0f09096af6f8f9865257e724 127.0.0.1:7001 master - 0 1402694957597 23 connected 566-5961 10923-11421";

	@Mock
	private RespClientBuilder<ClusterNode> builder;
	
	private List<ClusterNode> nodes;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		ClusterNodeParser parser = new DefaultClusterNodeParser();
		nodes = parser.parse(rawClusterInfo);
		prepareClientBuilder();
		ConnectionManager<ClusterNode> connectionManager = new RedisConnectionManagerImpl();
		connectionManager.setRespClientBuilder(builder);
		clients = connectionManager.buildAllClient(nodes);
	}
	
	private void prepareClientBuilder()
	{
		for (ClusterNode node:nodes)
		{
			try {
				when(builder.build(node)).thenReturn(new RedisRespClientImpl(node, false));
			} catch (IOException e) {
				// do nothing
			}
		}
	}
	
	@Test
	public void mappingTest()
	{
		SlotMappingService mappingService = new DefaultSlotMappingService();
		Map<Integer, RespClient> map = mappingService.buildMap(clients);
		Assert.assertNotNull(map);
		Assert.assertEquals(map.size(), ProtocolConstant.SLOT_MAX_SIZE);		
		for (Map.Entry<Integer, RespClient> entry: map.entrySet())
		{
			if (entry.getValue() == null)
			{
				System.err.println(entry.getKey() + ":" + entry.getValue());
			}
			Assert.assertNotNull(entry.getKey());
			Assert.assertNotNull(entry.getValue());
		}
	}
}
