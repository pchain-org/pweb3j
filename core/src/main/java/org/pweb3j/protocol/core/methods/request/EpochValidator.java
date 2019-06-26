package org.pweb3j.protocol.core.methods.request;

import java.math.BigInteger;

public class EpochValidator {

	String  address;
	String	public_key;
	BigInteger	voting_power;
	int remain_epoch;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPublic_key() {
		return public_key;
	}
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}
	public BigInteger getVoting_power() {
		return voting_power;
	}
	public void setVoting_power(BigInteger voting_power) {
		this.voting_power = voting_power;
	}
	public int getRemain_epoch() {
		return remain_epoch;
	}
	public void setRemain_epoch(int remain_epoch) {
		this.remain_epoch = remain_epoch;
	}
}
