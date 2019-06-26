package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;

public class ChainSignAddress extends Response<String> {

	public String getDATA() {
		return getResult();
	}
}
