package org.pweb3j.protocol.parity.methods.response;

import org.pweb3j.protocol.core.Response;

/**
 * trace_rawTransaction
 * trace_replayTransaction.
 */
public class ParityFullTraceResponse extends Response<FullTraceInfo> {
    public FullTraceInfo getFullTraceInfo() {
        return getResult();
    }
}
