package org.web3j.protocol.scenarios;

import static junit.framework.TestCase.assertFalse;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

public class DelegationFunctionIT extends Scenario {
	
	final String DefaultSelfSecurityDeposit = "10000000000000000000000"; // 10,000 * e18
	final String MinimumDelegationAmount    = "1000000000000000000000";  // 1000 * e18

	@Test
    public void testDelDelegate() throws Exception {

		String from = ALICE.getAddress();
		String candidate = ALICE.getAddress();
		BigInteger amount = new BigInteger(MinimumDelegationAmount); //1000*1.0e+18
		String chainId = MultiChainUtil.mainChainId;

		//ABIPack(pabi.Delegate.String(), candidate)
		Function function = new Function(
                "Delegate", 
                Arrays.asList(new Address(candidate)),
                Collections.<TypeReference<?>>emptyList()
                );
		
		String data = FunctionEncoder.encode(function);
        
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = RawTransaction.createTransaction(
	            nonce, MultiChainUtil.GAS_PRICE, MultiChainUtil.GAS_LIMIT, 
	            MultiChainUtil.ChainContractMagicAddr, amount, data);

	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
	    String hexValue = Numeric.toHexString(signedMessage);

	    Web3j web3j = MultiChainUtil.BuildWeb3j(chainId);
			
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
		BigInteger amount = new BigInteger(MinimumDelegationAmount); //1000*1.0e+18
		String chainId = MultiChainUtil.mainChainId;

		//ABIPack(pabi.CancelDelegate.String(), candidate, (*big.Int)(amount))
		Function function = new Function(
                "CancelDelegate", 
                Arrays.asList(new Address(candidate), new Uint(amount)),
                Collections.<TypeReference<?>>emptyList()
                );
		
		String data = FunctionEncoder.encode(function);
        
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
				nonce, MultiChainUtil.ChainContractMagicAddr, data);

	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
	    String hexValue = Numeric.toHexString(signedMessage);

	    Web3j web3j = MultiChainUtil.BuildWeb3j(chainId);
			
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
		BigInteger securityDeposit = new BigInteger(DefaultSelfSecurityDeposit);
		int commission = 10;
		String chainId = MultiChainUtil.mainChainId;

		//ABIPack(pabi.Candidate.String(), commission)
		Function function = new Function(
                "Candidate", 
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

	    Web3j web3j = MultiChainUtil.BuildWeb3j(chainId);

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

	    Web3j web3j = MultiChainUtil.BuildWeb3j(chainId);

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
