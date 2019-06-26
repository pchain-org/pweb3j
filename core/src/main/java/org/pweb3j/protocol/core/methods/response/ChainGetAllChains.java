package org.pweb3j.protocol.core.methods.response;

import java.util.List;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.protocol.core.methods.request.ChainStatus;

public class ChainGetAllChains extends Response<List<ChainStatus>> {

	public List<ChainStatus> getChains() {
		return getResult();
	}
}
