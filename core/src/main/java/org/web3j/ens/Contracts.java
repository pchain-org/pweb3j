package org.web3j.ens;

import java.math.BigInteger;

import org.web3j.crypto.TransactionEncoder;
import org.web3j.tx.ChainId;

/**
 * ENS registry contract addresses.
 */
//!!!
//not deployed yet
//!!!
public class Contracts {

    public static final String MAINNET = "0x314159265dd8dbb310642f98f50c066173c1259b";
    public static final String TESTNET = "0x314159265dd8dbb310642f98f50c066173c1259b";
    //public static final String ROPSTEN = "0x112234455c3a32fd11230c42e7bccd4a84e02010";
    //public static final String RINKEBY = "0xe7410170f87102df0055eb195163a03b7f2bff4a";

    public static String resolveRegistryContract(String chainId) {

    	BigInteger biChainId = TransactionEncoder.chainIdToBigInteger(chainId);
    	
    	if (biChainId.compareTo(ChainId.MAINNET) == 0) {
                return MAINNET;
    	} else if (biChainId.compareTo(ChainId.TESTNET) == 0) {
            return TESTNET;
    	} else {
            throw new EnsResolutionException(
                    "Unable to resolve ENS registry contract for network id: " + chainId);
        }
    }
}
