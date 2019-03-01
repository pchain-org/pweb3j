package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.FullBalance;

public class EthGetFullBalance extends Response<FullBalance> {

	public FullBalance getFullBalance() {
		return getResult();
	}
}
