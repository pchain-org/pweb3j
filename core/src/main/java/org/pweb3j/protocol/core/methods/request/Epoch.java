package org.pweb3j.protocol.core.methods.request;

import java.math.BigInteger;
import java.util.List;

public class Epoch {

	int number; //0,
	BigInteger reward_per_block; //1841338734567901234,
	BigInteger start_block; //0,
	BigInteger end_block; //2592000,
	String start_time; //"2018-09-30T16:20:07.02+08:00",
	String end_time; //"0001-01-01T00:00:00Z",
	BigInteger vote_start_block; //1944000,
	BigInteger vote_end_block; //2203199,
	BigInteger reveal_start_block; //2203200,
	BigInteger reveal_end_block; //2462400,
	int status; 
	List<EpochValidator> validators;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BigInteger getReward_per_block() {
		return reward_per_block;
	}
	public void setReward_per_block(BigInteger reward_per_block) {
		this.reward_per_block = reward_per_block;
	}
	public BigInteger getStart_block() {
		return start_block;
	}
	public void setStart_block(BigInteger start_block) {
		this.start_block = start_block;
	}
	public BigInteger getEnd_block() {
		return end_block;
	}
	public void setEnd_block(BigInteger end_block) {
		this.end_block = end_block;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public BigInteger getVote_start_block() {
		return vote_start_block;
	}
	public void setVote_start_block(BigInteger vote_start_block) {
		this.vote_start_block = vote_start_block;
	}
	public BigInteger getVote_end_block() {
		return vote_end_block;
	}
	public void setVote_end_block(BigInteger vote_end_block) {
		this.vote_end_block = vote_end_block;
	}
	public BigInteger getReveal_start_block() {
		return reveal_start_block;
	}
	public void setReveal_start_block(BigInteger reveal_start_block) {
		this.reveal_start_block = reveal_start_block;
	}
	public BigInteger getReveal_end_block() {
		return reveal_end_block;
	}
	public void setReveal_end_block(BigInteger reveal_end_block) {
		this.reveal_end_block = reveal_end_block;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<EpochValidator> getValidators() {
		return validators;
	}
	public void setValidators(List<EpochValidator> validators) {
		this.validators = validators;
	}
	public void addValidator(EpochValidator validator) {
		this.validators.add(validator);
	}
}
