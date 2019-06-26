package org.pweb3j.protocol.parity.methods.response;

import java.util.List;

import org.pweb3j.protocol.core.Response;

/**
 * trace_block
 * trace_filter
 * trace_transaction.
 */
public class ParityTracesResponse extends Response<List<Trace>> {
    public List<Trace> getTraces() {
        return getResult();
    }
}
