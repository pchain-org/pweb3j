package org.pweb3j.protocol.core.methods.response;

import java.util.List;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.protocol.core.methods.request.PeerInfo;

public class TdmPeers  extends Response<List<PeerInfo>> {

    public List<PeerInfo> getPeers() {
        return getResult();
    }
}
