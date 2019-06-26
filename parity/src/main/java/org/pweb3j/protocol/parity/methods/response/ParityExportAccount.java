package org.pweb3j.protocol.parity.methods.response;

import org.pweb3j.crypto.WalletFile;
import org.pweb3j.protocol.core.Response;

/**
 * parity_ExportAccount.
 */
public class ParityExportAccount extends Response<WalletFile> {
    public WalletFile getWallet() {
        return getResult();
    }
}
