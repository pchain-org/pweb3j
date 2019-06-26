package org.pweb3j.protocol.pantheon.response;

import org.pweb3j.protocol.core.Response;

public class PantheonFullDebugTraceResponse extends Response<FullDebugTraceInfo> {
    public FullDebugTraceInfo getFullDebugTraceInfo() {
        return getResult();
    }
}
