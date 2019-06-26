package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.protocol.core.methods.request.PrivateValidator;

public class TdmGeneratePrivateValidator extends Response<PrivateValidator> {
	public PrivateValidator getPrivateValidator() {
        return getResult();
    }
}
