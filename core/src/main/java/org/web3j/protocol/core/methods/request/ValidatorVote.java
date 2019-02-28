package org.web3j.protocol.core.methods.request;

public class ValidatorVote extends EpochValidator {

	String salt;
    String vote_hash;
    String tx_hash;
    
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getVote_hash() {
		return vote_hash;
	}
	public void setVote_hash(String vote_hash) {
		this.vote_hash = vote_hash;
	}
	public String getTx_hash() {
		return tx_hash;
	}
	public void setTx_hash(String tx_hash) {
		this.tx_hash = tx_hash;
	}
}
