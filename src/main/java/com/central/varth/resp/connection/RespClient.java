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

package com.central.varth.resp.connection;

import java.io.IOException;

import com.central.varth.resp.RespException;
import com.central.varth.resp.cluster.ClusterNode;
import com.central.varth.resp.type.RespType;

public interface RespClient {

	public void connect(String hostname, int port) throws IOException;
	
	public void connect(ClusterNode node) throws IOException;
	
	public <T extends RespType> T send(String command, Class<T> responseClass) throws IOException, RespException;
	
	public boolean hasSlot(int slot);
}
