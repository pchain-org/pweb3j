package org.pweb3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.utils.Numeric;

/**
 * eth_blockNumber.
 */
public class EthBlockNumber extends Response<String> {
    public BigInteger getBlockNumber() {
        return Numeric.decodeQuantity(getResult());
    }
}
