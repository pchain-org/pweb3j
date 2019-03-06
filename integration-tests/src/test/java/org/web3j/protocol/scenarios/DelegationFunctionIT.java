package org.web3j.protocol.scenarios;

import static junit.framework.TestCase.assertFalse;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

public class DelegationFunctionIT extends Scenario {


	@Test
    public void testDelDelegate() throws Exception {

		String from = ALICE.getAddress();
		String candidate = ALICE.getAddress();
		BigInteger amount = new BigInteger("10000000000000000000000");
		String chainId = MultiChainUtil.mainChainId;

		//ABIPack(pabi.Delegate.String(), candidate)
		Function function = new Function(
                "Delegate", 
                Arrays.asList(new Utf8String(candidate)),
                Collections.<TypeReference<?>>emptyList()
                );
		
		String data = FunctionEncoder.encode(function);
        
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = RawTransaction.createTransaction(
	            nonce, MultiChainUtil.GAS_PRICE, MultiChainUtil.GAS_LIMIT, 
	            MultiChainUtil.ChainContractMagicAddr, amount, data);

	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
	    String hexValue = Numeric.toHexString(signedMessage);

	    HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		Web3j web3j = Web3j.build(hs);
			
	    EthSendTransaction ethSendTransaction =
	                web3j.ethSendRawTransaction(hexValue).sendAsync().get();
	        
	    if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }
	        
	    String transactionHash = ethSendTransaction.getTransactionHash();
	    TransactionReceipt transactionReceipt =
	    		MultiChainUtil.WaitForTransactionReceipt(chainId, transactionHash);

	    assertFalse(transactionReceipt.toString().isEmpty());
	}
	
	@Test
    public void testDelCancelDelegate() throws Exception {

		String from = ALICE.getAddress();
		String candidate = ALICE.getAddress();
		BigInteger amount = new BigInteger("10000000000000000000000");
		String chainId = MultiChainUtil.mainChainId;

		//ABIPack(pabi.CancelDelegate.String(), candidate, (*big.Int)(amount))
		Function function = new Function(
                "CancelDelegate", 
                Arrays.asList(new Utf8String(candidate), new Uint(amount)),
                Collections.<TypeReference<?>>emptyList()
                );
		
		String data = FunctionEncoder.encode(function);
        
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
				nonce, MultiChainUtil.ChainContractMagicAddr, data);

	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
	    String hexValue = Numeric.toHexString(signedMessage);

	    HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		Web3j web3j = Web3j.build(hs);
			
	    EthSendTransaction ethSendTransaction =
	                web3j.ethSendRawTransaction(hexValue).sendAsync().get();
	        
	    if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }
	        
	    String transactionHash = ethSendTransaction.getTransactionHash();
	    TransactionReceipt transactionReceipt =
	    		MultiChainUtil.WaitForTransactionReceipt(chainId, transactionHash);

	    assertFalse(transactionReceipt.toString().isEmpty());
	}
	
	@Test
    public void testDelApplyCandidate() throws Exception {

		String from = ALICE.getAddress();
		BigInteger securityDeposit = new BigInteger("10000000000000000000000");
		int commission = 10;
		String chainId = MultiChainUtil.mainChainId;

		//ABIPack(pabi.Candidate.String(), commission)
		Function function = new Function(
                "ApplyCandidate", 
                Arrays.asList(new Uint8(commission)),
                Collections.<TypeReference<?>>emptyList()
                );
		
		String data = FunctionEncoder.encode(function);
        
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = RawTransaction.createTransaction(
	            nonce, MultiChainUtil.GAS_PRICE, MultiChainUtil.GAS_LIMIT, 
	            MultiChainUtil.ChainContractMagicAddr, securityDeposit, data);

	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
	    String hexValue = Numeric.toHexString(signedMessage);

	    HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		Web3j web3j = Web3j.build(hs);

	    EthSendTransaction ethSendTransaction =
	                web3j.ethSendRawTransaction(hexValue).sendAsync().get();

	    if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }

	    String transactionHash = ethSendTransaction.getTransactionHash();
	    TransactionReceipt transactionReceipt =
	    		MultiChainUtil.WaitForTransactionReceipt(chainId, transactionHash);

	    assertFalse(transactionReceipt.toString().isEmpty());
	}

	@Test
    public void testDelCancelCandidate() throws Exception {

		String from = ALICE.getAddress();
		String chainId = MultiChainUtil.mainChainId;

		//ABIPack(pabi.CancelCandidate.String())
		Function function = new Function(
                "CancelCandidate", 
                Collections.emptyList(),
                Collections.<TypeReference<?>>emptyList()
                );
		
		String data = FunctionEncoder.encode(function);
        
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
	            nonce, MultiChainUtil.ChainContractMagicAddr, data);

	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
	    String hexValue = Numeric.toHexString(signedMessage);

	    HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		Web3j web3j = Web3j.build(hs);

	    EthSendTransaction ethSendTransaction =
	                web3j.ethSendRawTransaction(hexValue).sendAsync().get();

	    if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }

	    String transactionHash = ethSendTransaction.getTransactionHash();
	    TransactionReceipt transactionReceipt =
	    		MultiChainUtil.WaitForTransactionReceipt(chainId, transactionHash);

	    assertFalse(transactionReceipt.toString().isEmpty());
	}
}
