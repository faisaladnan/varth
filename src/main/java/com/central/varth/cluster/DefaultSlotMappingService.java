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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.central.varth.resp.RespClient;
import com.central.varth.resp.RespException;
import com.central.varth.resp.cluster.ClusterNode;
import com.central.varth.resp.command.ClusterService;
import com.central.varth.resp.type.BulkString;

public class DefaultSlotMappingService implements SlotMappingService {

	
	@Override
	public Map<Integer, RespClient> buildMap(String rawClusterInfo) {
		return null;
	}

	@Override
	public RespClient getClient(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, RespClient> getSlotMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
