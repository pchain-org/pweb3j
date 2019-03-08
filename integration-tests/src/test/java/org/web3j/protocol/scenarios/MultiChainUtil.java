package org.web3j.protocol.scenarios;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

public class MultiChainUtil {
	protected final static int OFFICIAL_MINIMUM_VALIDATORS = 1;
	protected final static String OFFICIAL_MINIMUM_DEPOSIT = "100000000000000000000000"; // 100,000 * e18
	protected final static String ChainContractMagicAddr = "0x0000000000000000000000000000000000000065";
	protected static final BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);
	protected static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);
    
    public static String mainChainId = "pchain";
	public static String child0Id = "child_100";

	protected static String node = "http://localhost:6969/";
	private static final int SLEEP_DURATION = 2000;
    private static final int ATTEMPTS = 40;
    
	
	protected static void SetNode(String node) {
		MultiChainUtil.node = node;
	}
	
	protected static String GetNode() {
		return MultiChainUtil.node;
	}
	
	protected static Web3j BuildWeb3j(String chainId) {
		
		HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		return Web3j.build(hs);
	}

	protected static String Version() {
		String result = "call failed";
		
		HttpService hs = new HttpService(node + mainChainId);
		try {
			Web3j web3 = Web3j.build(hs); 
			Web3ClientVersion web3ClientVersion;

			web3ClientVersion = web3.web3ClientVersion().send();
			result = web3ClientVersion.getWeb3ClientVersion();
			
			hs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	protected static BigInteger EthBlockNumber(String chainId) {
		HttpService hs = new HttpService(node + chainId);
		Web3j web3 = Web3j.build(hs);
		
		EthBlockNumber ethBlockNumber = new EthBlockNumber();
		try {
			ethBlockNumber = web3.ethBlockNumber().send();
			hs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ethBlockNumber.getBlockNumber();
	}
	
	protected static String GetBalance(String chainId, String account) {
		
		HttpService hs = new HttpService(node + chainId);
		Web3j web3 = Web3j.build(hs);
		EthGetBalance ethGetBalance = new EthGetBalance();
		try {
			ethGetBalance = web3.ethGetBalance(
					account, DefaultBlockParameterName.LATEST).send();
			
			hs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String result = ethGetBalance.getResult();
		result = Numeric.cleanHexPrefix(result);
		BigInteger bi = new BigInteger(result, 16);
		result = bi.toString();
		return result;
	}

	protected static String Call(String chain, String contract, String method, String param) {
		System.out.println(String.format("chain:%s, contract:%s, method:%s, param:%s",
				chain, contract, method, param));
		return null;
	}
	

	protected static BigInteger GetNonce(String chainId, String address) throws Exception {
		
		HttpService hs = new HttpService(node + chainId);
		Web3j web3 = Web3j.build(hs);
		
        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();

        return ethGetTransactionCount.getTransactionCount();
    }
    
	protected static TransactionReceipt WaitForTransactionReceipt(String chainId,
            String transactionHash) throws Exception {

        Optional<TransactionReceipt> transactionReceiptOptional =
                GetTransactionReceipt(chainId, transactionHash, SLEEP_DURATION, ATTEMPTS);

        if (!transactionReceiptOptional.isPresent()) {
        	String error = "Transaction receipt not generated after " + ATTEMPTS + " attempts";
            System.out.println(error);
            throw new Exception(error);
        }

        return transactionReceiptOptional.get();
    }
    
	protected static Optional<TransactionReceipt> GetTransactionReceipt(String chainId,
            String transactionHash, int sleepDuration, int attempts) throws Exception {

        Optional<TransactionReceipt> receiptOptional =
                SendTransactionReceiptRequest(chainId, transactionHash);
        for (int i = 0; i < attempts; i++) {
            if (!receiptOptional.isPresent()) {
                Thread.sleep(sleepDuration);
                receiptOptional = SendTransactionReceiptRequest(chainId, transactionHash);
            } else {
                break;
            }
        }

        return receiptOptional;
    }
    
	protected static Optional<TransactionReceipt> SendTransactionReceiptRequest(String chainId,
            String transactionHash) throws Exception {
    	
    	HttpService hs = new HttpService(node + chainId);
		Web3j web3 = Web3j.build(hs);
		
        EthGetTransactionReceipt transactionReceipt =
                web3.ethGetTransactionReceipt(transactionHash).sendAsync().get();

        return transactionReceipt.getTransactionReceipt();
    }
	

    public static RawTransaction CreateEtherTransaction(BigInteger nonce, String to, BigInteger value) {
        return CreateEtherTransaction(nonce, to, GAS_LIMIT, GAS_PRICE, value);
    }
    
    public static RawTransaction CreateEtherTransaction(BigInteger nonce, String to, 
    		BigInteger gasLimit, BigInteger gasPrice, BigInteger value) {
        
        return RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value);
    }
    
    public static RawTransaction CreateTransaction(BigInteger nonce, String to, String data) {
        return CreateTransaction(nonce, to, GAS_LIMIT, GAS_PRICE, data);
    }
    
    public static RawTransaction CreateTransaction(BigInteger nonce, String to, 
    		BigInteger gasLimit, BigInteger gasPrice, String data) {
        
        return RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, data);
    }
}
