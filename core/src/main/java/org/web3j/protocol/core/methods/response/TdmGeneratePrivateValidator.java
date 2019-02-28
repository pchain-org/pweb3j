package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.PrivateValidator;

public class TdmGeneratePrivateValidator extends Response<PrivateValidator> {
	public PrivateValidator getPrivateValidator() {
        return getResult();
    }
}
