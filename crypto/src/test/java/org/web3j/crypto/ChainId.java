package org.web3j.crypto;

import java.math.BigInteger;

import org.web3j.crypto.TransactionEncoder;

/**
 * Ethereum chain ids as per
 * <a href="https://github.com/ethereum/EIPs/blob/master/EIPS/eip-155.md">EIP-155</a>.
 */
public class ChainId {
	
	public static final String MAINNET_STR = "pchain";
    public static final String TESTNET_STR = "testnet";
    
    
    public static final BigInteger NONE = BigInteger.valueOf(-1);
    public static final BigInteger MAINNET = TransactionEncoder.chainIdToBigInteger(MAINNET_STR);
    public static final BigInteger TESTNET = TransactionEncoder.chainIdToBigInteger(TESTNET_STR);
    
    /*
    public static final byte EXPANSE_MAINNET = 2;
    public static final byte ROPSTEN = 3;
    public static final byte RINKEBY = 4;
    public static final byte ROOTSTOCK_MAINNET = 30;
    public static final byte ROOTSTOCK_TESTNET = 31;
    public static final byte KOVAN = 42;
    public static final byte ETHEREUM_CLASSIC_MAINNET = 61;
    public static final byte ETHEREUM_CLASSIC_TESTNET = 62;
    */
}
