package org.web3j.protocol.core.methods.response;

import java.util.List;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.EpochValidator;

public class TdmGetNextEpochValidators  extends Response<List<EpochValidator>>{
	
	public List<EpochValidator> getEpochValidators() {
		return getResult();
	}
}
