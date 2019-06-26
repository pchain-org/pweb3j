package org.pweb3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.utils.Numeric;

/**
 * net_peerCount.
 */
public class NetPeerCount extends Response<String> {

    public BigInteger getQuantity() {
        return Numeric.decodeQuantity(getResult());
    }
}
