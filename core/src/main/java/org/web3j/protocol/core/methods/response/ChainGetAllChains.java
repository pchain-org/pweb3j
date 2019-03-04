package org.web3j.protocol.core.methods.response;

import java.util.List;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.ChainStatus;

public class ChainGetAllChains extends Response<List<ChainStatus>> {

	public List<ChainStatus> getChains() {
		return getResult();
	}
}
