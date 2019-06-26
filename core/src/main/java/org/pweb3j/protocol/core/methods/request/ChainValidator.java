package org.pweb3j.protocol.core.methods.request;

import java.math.BigInteger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ChainValidator {

	String address;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger voting_power;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigInteger getVoting_power() {
		return voting_power;
	}
	public void setVoting_power(BigInteger voting_power) {
		this.voting_power = voting_power;
	}
}
