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

package com.central.varth.resp.cluster;

import java.util.ArrayList;
import java.util.List;

public class ClusterNode {
	
	private String nodeId;
	private String addressPort;
	private String flags;
	private String masterNodeId;
	private String lastPendingPing;
	private String lastPongReceived;
	private String epoch;
	private String status;
	private List<String> slots = new ArrayList<String>();
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getAddressPort() {
		return addressPort;
	}
	public void setAddressPort(String addressPort) {
		this.addressPort = addressPort;
	}
	public String getFlags() {
		return flags;
	}
	public void setFlags(String flags) {
		this.flags = flags;
	}
	public String getMasterNodeId() {
		return masterNodeId;
	}
	public void setMasterNodeId(String masterNodeId) {
		this.masterNodeId = masterNodeId;
	}
	public String getLastPendingPing() {
		return lastPendingPing;
	}
	public void setLastPendingPing(String lastPendingPing) {
		this.lastPendingPing = lastPendingPing;
	}
	public String getLastPongReceived() {
		return lastPongReceived;
	}
	public void setLastPongReceived(String lastPongReceived) {
		this.lastPongReceived = lastPongReceived;
	}
	public String getEpoch() {
		return epoch;
	}
	public void setEpoch(String epoch) {
		this.epoch = epoch;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getSlots() {
		return slots;
	}
	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
		
}
