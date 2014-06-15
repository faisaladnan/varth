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

import java.io.IOException;

import com.central.varth.resp.cluster.ClusterNode;
import com.central.varth.resp.connection.RespClient;
import com.central.varth.resp.connection.RespClientBuilder;

public class RedisRespClientBuilder implements RespClientBuilder<ClusterNode> {

	@Override
	public RespClient build(ClusterNode params) {
		RespClient client = null;
		try {
			client = new RedisRespClientImpl(params);
		} catch (IOException e) {
			// do nothing
		}
		return client;
	}

}
