package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.protocol.core.methods.request.Epoch;

public class TdmGetEpoch extends Response<Epoch> {

	public Epoch getEpoch() {
		return getResult();
	}
}
