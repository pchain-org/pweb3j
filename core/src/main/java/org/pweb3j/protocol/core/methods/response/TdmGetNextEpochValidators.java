package org.pweb3j.protocol.core.methods.response;

import java.util.List;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.protocol.core.methods.request.EpochValidator;

public class TdmGetNextEpochValidators  extends Response<List<EpochValidator>>{
	
	public List<EpochValidator> getEpochValidators() {
		return getResult();
	}
}
