package org.pweb3j.tx;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import org.pweb3j.crypto.Credentials;
import org.pweb3j.crypto.SampleKeys;
import org.pweb3j.protocol.core.methods.response.TransactionReceipt;
import org.pweb3j.tx.exceptions.TxHashMismatchException;
import org.pweb3j.utils.Convert;

public class RawTransactionManagerTest extends ManagedTransactionTester {

    @Test(expected = TxHashMismatchException.class)
    public void testTxHashMismatch() throws Exception {
        TransactionReceipt transactionReceipt = prepareTransfer();
        prepareTransaction(transactionReceipt);

        TransactionManager transactionManager =
                new RawTransactionManager(web3j, SampleKeys.CREDENTIALS);
        Transfer transfer = new Transfer(web3j, transactionManager);
        transfer.sendFunds(ADDRESS, BigDecimal.ONE, Convert.Unit.ETHER).send();
    }
}
