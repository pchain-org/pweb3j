package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;

public class EthSubscribe extends Response<String> {
    public String getSubscriptionId() {
        return getResult();
    }
}
