package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.utils.Numeric;

import java.math.BigInteger;

public class EthChainId extends Response<String> {

    public BigInteger getChainId() {
        return Numeric.decodeQuantity(getResult());
    }
}
