package org.pweb3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.utils.Numeric;

/**
 * eth_getBlockTransactionCountByNumber.
 */
public class EthGetBlockTransactionCountByNumber extends Response<String> {
    public BigInteger getTransactionCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
