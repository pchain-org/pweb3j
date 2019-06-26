package org.pweb3j.protocol.pantheon.response;

import java.util.Map;

import org.pweb3j.protocol.core.Response;


public class PantheonEthAccountsMapResponse extends Response<Map<String, Boolean>> {
    public Map<String, Boolean> getAccounts() {
        return getResult();
    }
}
