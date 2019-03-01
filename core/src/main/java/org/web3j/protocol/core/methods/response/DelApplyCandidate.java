package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

public class DelApplyCandidate extends Response<String> {

	public String getHash() {
		return getResult();
	}
}
