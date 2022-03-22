package org.pweb3j.protocol.core.methods.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigInteger;
import java.util.Map;

public class PeerInfo {
    String ID;
    String Name;
    String[] Caps;
    Network network;
    Map<String, Object> Protocols; // Sub-protocol specific metadata fields

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String[] getCaps() {
        return Caps;
    }
    public void setCaps(String[] caps) {
        Caps = caps;
    }
    public Network getNetwork() {
        return network;
    }
    public void setNetwork(Network network) {
        this.network = network;
    }
    public Map<String, Object> getProtocols() {
        return Protocols;
    }
    public void setProtocols(Map<String, Object> protocols) {
        Protocols = protocols;
    }


    public static class Network {
        String  LocalAddress;  // Local endpoint of the TCP data connection
        String  RemoteAddress;// Remote endpoint of the TCP data connection
        Boolean Inbound;
        Boolean Trusted;
        Boolean Static;

        public String getLocalAddress() {
            return LocalAddress;
        }
        public void setLocalAddress(String localAddress) {
            LocalAddress = localAddress;
        }
        public String getRemoteAddress() {
            return RemoteAddress;
        }
        public void setRemoteAddress(String remoteAddress) {
            RemoteAddress = remoteAddress;
        }
        public Boolean getInbound() {
            return Inbound;
        }
        public void setInbound(Boolean inbound) {
            Inbound = inbound;
        }
        public Boolean getTrusted() {
            return Trusted;
        }
        public void setTrusted(Boolean trusted) {
            Trusted = trusted;
        }
        public Boolean getStatic() {
            return Static;
        }
        public void setStatic(Boolean aStatic) {
            Static = aStatic;
        }
    }
}
