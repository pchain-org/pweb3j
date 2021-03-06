package org.pweb3j.crypto;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.pweb3j.crypto.SecureRandomUtils.isAndroidRuntime;
import static org.pweb3j.crypto.SecureRandomUtils.secureRandom;

public class SecureRandomUtilsTest {

    @Test
    public void testSecureRandom() {
        secureRandom().nextInt();
    }

    @Test
    public void testIsNotAndroidRuntime() {
        assertFalse(isAndroidRuntime());
    }
}
