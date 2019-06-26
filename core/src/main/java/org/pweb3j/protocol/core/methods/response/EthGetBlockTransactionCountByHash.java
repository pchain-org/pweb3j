package org.pweb3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.utils.Numeric;

/**
 * eth_getBlockTransactionCountByHash.
 */
public class EthGetBlockTransactionCountByHash extends Response<String> {
    public BigInteger getTransactionCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
