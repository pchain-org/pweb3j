package org.pweb3j.tx;

import java.io.IOException;
import java.math.BigInteger;

import org.pweb3j.crypto.Credentials;
import org.pweb3j.crypto.Hash;
import org.pweb3j.crypto.RawTransaction;
import org.pweb3j.crypto.TransactionEncoder;
import org.pweb3j.protocol.Web3j;
import org.pweb3j.protocol.core.DefaultBlockParameterName;
import org.pweb3j.protocol.core.methods.response.EthGetTransactionCount;
import org.pweb3j.protocol.core.methods.response.EthSendTransaction;
import org.pweb3j.tx.exceptions.TxHashMismatchException;
import org.pweb3j.tx.response.TransactionReceiptProcessor;
import org.pweb3j.utils.Numeric;
import org.pweb3j.utils.TxHashVerifier;

/**
 * TransactionManager implementation using Ethereum wallet file to create and sign transactions
 * locally.
 *
 * <p>This transaction manager provides support for specifying the chain id for transactions as per
 * <a href="https://github.com/ethereum/EIPs/issues/155">EIP155</a>, as well as for locally signing
 * RawTransaction instances without broadcasting them.
 */
public class RawTransactionManager extends TransactionManager {

    private final Web3j web3j;
    final Credentials credentials;

    private final BigInteger chainId;

    protected TxHashVerifier txHashVerifier = new TxHashVerifier();

    
    public RawTransactionManager(Web3j web3j, Credentials credentials, BigInteger chainId) {
        super(web3j, credentials.getAddress());

        this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(Web3j web3j, Credentials credentials, String chainId) {
        this(web3j, credentials, TransactionEncoder.chainIdToBigInteger(chainId));
    }
    
    public RawTransactionManager(
            Web3j web3j, Credentials credentials, BigInteger chainId,
            TransactionReceiptProcessor transactionReceiptProcessor) {
    	super(transactionReceiptProcessor, credentials.getAddress());
    	
    	this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(
            Web3j web3j, Credentials credentials, String chainId,
            TransactionReceiptProcessor transactionReceiptProcessor) {
    	
        this(web3j, credentials, TransactionEncoder.chainIdToBigInteger(chainId));
    }
    
    public RawTransactionManager(
            Web3j web3j, Credentials credentials, BigInteger chainId, int attempts, long sleepDuration) {
        super(web3j, attempts, sleepDuration, credentials.getAddress());

        this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(
            Web3j web3j, Credentials credentials, String chainId, int attempts, long sleepDuration) {
        this(web3j, credentials, TransactionEncoder.chainIdToBigInteger(chainId), attempts, sleepDuration);
    }

    public RawTransactionManager(Web3j web3j, Credentials credentials) {
        this(web3j, credentials, ChainId.NONE);
    }

    public RawTransactionManager(
            Web3j web3j, Credentials credentials, int attempts, int sleepDuration) {
        this(web3j, credentials, ChainId.NONE, attempts, sleepDuration);
    }

    protected BigInteger getNonce() throws IOException {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.PENDING).send();

        return ethGetTransactionCount.getTransactionCount();
    }

    public TxHashVerifier getTxHashVerifier() {
        return txHashVerifier;
    }

    public void setTxHashVerifier(TxHashVerifier txHashVerifier) {
        this.txHashVerifier = txHashVerifier;
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value) throws IOException {

        BigInteger nonce = getNonce();

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                to,
                value,
                data);

        return signAndSend(rawTransaction);
    }
    
    /*
     * @param rawTransaction a RawTransaction istance to be signed
     * @return The transaction signed and encoded without ever broadcasting it
     */
    public String sign(RawTransaction rawTransaction) {

        byte[] signedMessage;

        if (chainId.compareTo(ChainId.NONE) > 0) {
            signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
        } else {
            signedMessage = TransactionEncoder.signMessage(rawTransaction, ChainId.MAINNET_STR, credentials);
        }

        return Numeric.toHexString(signedMessage);
    }

    public EthSendTransaction signAndSend(RawTransaction rawTransaction)
            throws IOException {
        String hexValue = sign(rawTransaction);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();

        if (ethSendTransaction != null && !ethSendTransaction.hasError()) {
            String txHashLocal = Hash.sha3(hexValue);
            String txHashRemote = ethSendTransaction.getTransactionHash();
            if (!txHashVerifier.verify(txHashLocal, txHashRemote)) {
                throw new TxHashMismatchException(txHashLocal, txHashRemote);
            }
        }

        return ethSendTransaction;
    }
}
