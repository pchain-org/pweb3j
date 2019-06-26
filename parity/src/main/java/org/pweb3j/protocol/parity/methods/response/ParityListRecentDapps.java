package org.pweb3j.protocol.parity.methods.response;

import java.util.ArrayList;

import org.pweb3j.protocol.core.Response;

/**
 * parity_ListRecentDapps.
 */
public class ParityListRecentDapps extends Response<ArrayList<String>> {
    public ArrayList<String> getDappsIds() {
        return getResult();
    }
}
