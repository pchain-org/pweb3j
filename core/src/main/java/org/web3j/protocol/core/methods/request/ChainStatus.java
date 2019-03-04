package org.web3j.protocol.core.methods.request;

import java.util.List;

public class ChainStatus {

	String chain_id;
	String owner;
	int current_epoch;
	String epoch_start_time;
	List<ChainValidator> validators;
	
	public String getChain_id() {
		return chain_id;
	}
	public void setChain_id(String chain_id) {
		this.chain_id = chain_id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getCurrent_epoch() {
		return current_epoch;
	}
	public void setCurrent_epoch(int current_epoch) {
		this.current_epoch = current_epoch;
	}
	public String getEpoch_start_time() {
		return epoch_start_time;
	}
	public void setEpoch_start_time(String epoch_start_time) {
		this.epoch_start_time = epoch_start_time;
	}
	public List<ChainValidator> getValidators() {
		return validators;
	}
	public void setValidators(List<ChainValidator> validators) {
		this.validators = validators;
	}
}
