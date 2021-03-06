package org.pweb3j.protocol.admin.methods.response;

import org.pweb3j.protocol.core.Response;

/**
 * Boolean response type.
 */
public class BooleanResponse extends Response<Boolean> {
    public boolean success() {
        return getResult();
    }
}
