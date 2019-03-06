package org.web3j.crypto;

import java.math.BigInteger;
import java.security.SignatureException;

import org.web3j.utils.Numeric;

public class SignedRawTransaction extends RawTransaction {

    private static final int CHAIN_ID_INC = 35;
    private static final int LOWER_REAL_V = 27;

    private Sign.SignatureData signatureData;

    public SignedRawTransaction(BigInteger nonce, BigInteger gasPrice,
            BigInteger gasLimit, String to, BigInteger value, String data,
            Sign.SignatureData signatureData) {
        super(nonce, gasPrice, gasLimit, to, value, data);
        this.signatureData = signatureData;
    }

    public Sign.SignatureData getSignatureData() {
        return signatureData;
    }

    public String getFrom() throws SignatureException {
        BigInteger chainId = getChainId();
        byte[] encodedTransaction;
        //if (null == chainId) {
        //    encodedTransaction = TransactionEncoder.encode(this);
        //} else {
            encodedTransaction = TransactionEncoder.encode(this, chainId);
        //}
        byte v = signatureData.getV()[31];
        byte[] r = signatureData.getR();
        byte[] s = signatureData.getS();
        Sign.SignatureData signatureDataV = new Sign.SignatureData(getRealV(v), r, s);
        BigInteger key = Sign.signedMessageToKey(encodedTransaction, signatureDataV);
        return "0x" + Keys.getAddress(key);
    }

    public void verify(String from) throws SignatureException {
        String actualFrom = getFrom();
        if (!actualFrom.equals(from)) {
            throw new SignatureException("from mismatch");
        }
    }

    public byte[] getRealV(byte v) {
    	
    	byte realV = LOWER_REAL_V;
        if (v == LOWER_REAL_V || v == (LOWER_REAL_V + 1)) {
        	realV = v;
        } else {
        	int inc = 0;
        	if ((int) v % 2 == 0) {
        		inc = 1;
        	}
        	realV = (byte) (realV + inc);
        }
        byte[] r = Numeric.toBytesPadded(BigInteger.valueOf(realV), 32);
        return r;
    }

    public BigInteger getChainId() {
        byte[] v = signatureData.getV();
        byte v31 = v[31];
        if (v31 == LOWER_REAL_V || v31 == (LOWER_REAL_V + 1)) {
            return null;
        }
        BigInteger chainId = new BigInteger(v).subtract(BigInteger.valueOf(CHAIN_ID_INC)).divide(BigInteger.valueOf(2));
        return chainId;
    }
}
