package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;

/**
 * db_putString.
 */
public class DbPutString extends Response<Boolean> {

    public boolean valueStored() {
        return getResult();
    }
}
