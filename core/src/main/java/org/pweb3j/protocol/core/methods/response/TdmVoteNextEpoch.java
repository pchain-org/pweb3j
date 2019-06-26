package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;

public class TdmVoteNextEpoch  extends Response<String>{
	public String getHash() {
        return getResult();
    }
}
