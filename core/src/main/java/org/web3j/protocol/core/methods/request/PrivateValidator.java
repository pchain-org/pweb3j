package org.web3j.protocol.core.methods.request;

public class PrivateValidator {
	
	String address;
    String consensus_pub_key;
    String consensus_priv_key;
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConsensus_pub_key() {
		return consensus_pub_key;
	}
	public void setConsensus_pub_key(String consensus_pub_key) {
		this.consensus_pub_key = consensus_pub_key;
	}
	public String getConsensus_priv_key() {
		return consensus_priv_key;
	}
	public void setConsensus_priv_key(String consensus_priv_key) {
		this.consensus_priv_key = consensus_priv_key;
	}

}
