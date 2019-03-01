package org.web3j.protocol.core.methods.request;

import java.math.BigInteger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ProxiedDetail {

	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger ProxiedBalance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
    BigInteger DepositProxiedBalance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
    BigInteger PendingRefundBalance;
    
	public BigInteger getProxiedBalance() {
		return ProxiedBalance;
	}
	public void setProxiedBalance(BigInteger proxiedBalance) {
		ProxiedBalance = proxiedBalance;
	}
	public BigInteger getDepositProxiedBalance() {
		return DepositProxiedBalance;
	}
	public void setDepositProxiedBalance(BigInteger depositProxiedBalance) {
		DepositProxiedBalance = depositProxiedBalance;
	}
	public BigInteger getPendingRefundBalance() {
		return PendingRefundBalance;
	}
	public void setPendingRefundBalance(BigInteger pendingRefundBalance) {
		PendingRefundBalance = pendingRefundBalance;
	}
}
