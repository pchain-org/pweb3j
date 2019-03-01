package org.web3j.protocol.core.methods.request;

public class CandidateState {
	
	boolean candidate;
    int commission;
    
	public boolean isCandidate() {
		return candidate;
	}
	public void setCandidate(boolean candidate) {
		this.candidate = candidate;
	}
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
    
}
