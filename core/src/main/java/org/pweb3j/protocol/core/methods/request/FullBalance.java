package org.pweb3j.protocol.core.methods.request;

import java.math.BigInteger;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class FullBalance {
	
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger balance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger total_delegateBalance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger total_depositBalance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger total_depositProxiedBalance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger total_pendingRefundBalance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger total_proxiedBalance;
	@JsonDeserialize(using = BigIntegerDeserializer.class)
	BigInteger total_rewardBalance;
	Map<String, ProxiedDetail> proxied_detail;
	@JsonDeserialize(contentUsing = BigIntegerDeserializer.class)
	Map<String, BigInteger> reward_detail;
	
	public BigInteger getBalance() {
		return balance;
	}
	public void setBalance(BigInteger balance) {
		this.balance = balance;
	}
	public BigInteger getTotal_delegateBalance() {
		return total_delegateBalance;
	}
	public void setTotal_delegateBalance(BigInteger total_delegateBalance) {
		this.total_delegateBalance = total_delegateBalance;
	}
	public BigInteger getTotal_depositBalance() {
		return total_depositBalance;
	}
	public void setTotal_depositBalance(BigInteger total_depositBalance) {
		this.total_depositBalance = total_depositBalance;
	}
	public BigInteger getTotal_depositProxiedBalance() {
		return total_depositProxiedBalance;
	}
	public void setTotal_depositProxiedBalance(BigInteger total_depositProxiedBalance) {
		this.total_depositProxiedBalance = total_depositProxiedBalance;
	}
	public BigInteger getTotal_pendingRefundBalance() {
		return total_pendingRefundBalance;
	}
	public void setTotal_pendingRefundBalance(BigInteger total_pendingRefundBalance) {
		this.total_pendingRefundBalance = total_pendingRefundBalance;
	}
	public BigInteger getTotal_proxiedBalance() {
		return total_proxiedBalance;
	}
	public void setTotal_proxiedBalance(BigInteger total_proxiedBalance) {
		this.total_proxiedBalance = total_proxiedBalance;
	}
	public BigInteger getTotal_rewardBalance() {
		return total_rewardBalance;
	}
	public void setTotal_rewardBalance(BigInteger total_rewardBalance) {
		this.total_rewardBalance = total_rewardBalance;
	}
	public Map<String, ProxiedDetail> getProxied_detail() {
		return proxied_detail;
	}
	public void setProxied_detail(Map<String, ProxiedDetail> proxied_detail) {
		this.proxied_detail = proxied_detail;
	}
	public Map<String, BigInteger> getReward_detail() {
		return reward_detail;
	}
	public void setReward_detail(Map<String, BigInteger> reward_detail) {
		this.reward_detail = reward_detail;
	}
	
}
