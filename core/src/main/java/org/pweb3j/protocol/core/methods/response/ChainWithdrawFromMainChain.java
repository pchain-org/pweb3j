package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;

public class ChainWithdrawFromMainChain extends Response<String> {

	public String getHash() {
		return getResult();
	}
}
