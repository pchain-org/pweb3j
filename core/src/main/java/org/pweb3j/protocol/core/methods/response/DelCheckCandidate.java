package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.protocol.core.methods.request.CandidateState;

public class DelCheckCandidate extends Response<CandidateState> {

	public CandidateState getCandidateState() {
		return getResult();
	}
}
