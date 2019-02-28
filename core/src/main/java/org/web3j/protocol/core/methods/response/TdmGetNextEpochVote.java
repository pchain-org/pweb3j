package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.EpochVote;

public class TdmGetNextEpochVote extends Response<EpochVote> {

	public EpochVote getVote() {
		return getResult();
	}
}
