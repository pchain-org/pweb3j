package org.pweb3j.console;

import org.junit.Before;

import org.pweb3j.TempFileProvider;

import static org.mockito.Mockito.mock;
import static org.pweb3j.crypto.SampleKeys.PASSWORD;

public abstract class WalletTester extends TempFileProvider {

    static final char[] WALLET_PASSWORD = PASSWORD.toCharArray();

    IODevice console;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        console = mock(IODevice.class);
    }
}
