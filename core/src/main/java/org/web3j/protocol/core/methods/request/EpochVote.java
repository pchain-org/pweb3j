package org.web3j.protocol.core.methods.request;

import java.util.List;

public class EpochVote {
	
	int vote_for_epoch;
    int start_block;
    int end_block;
    List<ValidatorVote> votes;
    
	public int getVote_for_epoch() {
		return vote_for_epoch;
	}
	public void setVote_for_epoch(int vote_for_epoch) {
		this.vote_for_epoch = vote_for_epoch;
	}
	public int getStart_block() {
		return start_block;
	}
	public void setStart_block(int start_block) {
		this.start_block = start_block;
	}
	public int getEnd_block() {
		return end_block;
	}
	public void setEnd_block(int end_block) {
		this.end_block = end_block;
	}
	public List<ValidatorVote> getVotes() {
		return votes;
	}
	public void setVotes(List<ValidatorVote> votes) {
		this.votes = votes;
	}

}
