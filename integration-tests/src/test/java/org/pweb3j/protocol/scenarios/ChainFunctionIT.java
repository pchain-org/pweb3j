package org.pweb3j.protocol.scenarios;

import static junit.framework.TestCase.assertFalse;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.pweb3j.abi.FunctionEncoder;
import org.pweb3j.abi.TypeReference;
import org.pweb3j.abi.datatypes.DynamicBytes;
import org.pweb3j.abi.datatypes.Function;
import org.pweb3j.abi.datatypes.Uint;
import org.pweb3j.abi.datatypes.Utf8String;
import org.pweb3j.abi.datatypes.generated.Bytes32;
import org.pweb3j.abi.datatypes.generated.Uint16;
import org.pweb3j.crypto.RawTransaction;
import org.pweb3j.crypto.TransactionEncoder;
import org.pweb3j.protocol.Web3j;
import org.pweb3j.protocol.core.methods.response.EthSendTransaction;
import org.pweb3j.protocol.core.methods.response.TransactionReceipt;
import org.pweb3j.utils.Numeric;

public class ChainFunctionIT extends Scenario {
	
	final static String newChildChainId = "child_it_0";
	
	@Test
    public void testChainCreateChildChain() throws Exception {

		String from = ALICE.getAddress();
		String chainId = newChildChainId;
		
		BigInteger blockNumber = MultiChainUtil.EthBlockNumber(MultiChainUtil.mainChainId);
		BigInteger startBlock = blockNumber.add(BigInteger.valueOf(5));
		BigInteger endBlock = startBlock.add(BigInteger.valueOf(100));
		Uint16 minValidators = new Uint16(MultiChainUtil.OFFICIAL_MINIMUM_VALIDATORS);
		BigInteger minDepositAmount = new BigInteger(MultiChainUtil.OFFICIAL_MINIMUM_DEPOSIT);

		//pabi.ChainABI.Pack(pabi.CreateChildChain.String(), chainId, 
		//uint16(*minValidators), (*big.Int)(minDepositAmount), 
		//(*big.Int)(startBlock), (*big.Int)(endBlock))
		Function function = new Function(
                "CreateChildChain", 
                Arrays.asList(new Utf8String(chainId), new Uint16(minValidators.getValue()), 
                				new Uint(minDepositAmount), new Uint(startBlock), new Uint(endBlock)),
                Collections.<TypeReference<?>>emptyList()
                );
		
		String data = FunctionEncoder.encode(function);
        
		BigInteger nonce = MultiChainUtil.GetNonce(MultiChainUtil.mainChainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
					nonce, MultiChainUtil.ChainContractMagicAddr, data);

	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, MultiChainUtil.mainChainId, ALICE);
	    String hexValue = Numeric.toHexString(signedMessage);

	    Web3j web3j = MultiChainUtil.BuildWeb3j(MultiChainUtil.mainChainId);

	    EthSendTransaction ethSendTransaction =
	                web3j.ethSendRawTransaction(hexValue).sendAsync().get();
	        
	    if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }
	        
	    String transactionHash = ethSendTransaction.getTransactionHash();
	    TransactionReceipt transactionReceipt =
	    		MultiChainUtil.WaitForTransactionReceipt(MultiChainUtil.mainChainId, transactionHash);

	    assertFalse(transactionReceipt.toString().isEmpty());
	}
	
	@Test
	public void ChainJoinChildChain() throws Exception {
		
		String from = ALICE.getAddress();
		String chainId = newChildChainId;
		String pubkey = ALICE_BLS_PUBKEY;
		String signature = "0x8189a1f9432649ef8708e76e00448168e177667ba16a68ae4650b1b3eab0ea4d154c58ccb4f422422a7db37911d90164271d4e8dd18683dde60286bed4adede1";
		
		//ABIPack(pabi.JoinChildChain.String(), pubkey.Bytes(), chainId, signature)
		Function function = new Function(
				"JoinChildChain", 
				Arrays.asList(new DynamicBytes(Numeric.hexStringToByteArray(pubkey)), 
						new Utf8String(chainId), 
						new DynamicBytes(Numeric.hexStringToByteArray(signature))),
				Collections.<TypeReference<?>>emptyList()
				);

		String data = FunctionEncoder.encode(function);

		BigInteger nonce = MultiChainUtil.GetNonce(MultiChainUtil.mainChainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(nonce, MultiChainUtil.ChainContractMagicAddr, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, MultiChainUtil.mainChainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		Web3j web3j = MultiChainUtil.BuildWeb3j(MultiChainUtil.mainChainId);

		EthSendTransaction ethSendTransaction =
				web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }
	        
	    String transactionHash = ethSendTransaction.getTransactionHash();

		TransactionReceipt transactionReceipt =
				MultiChainUtil.WaitForTransactionReceipt(MultiChainUtil.mainChainId, transactionHash);
				
		assertFalse(transactionReceipt.toString().isEmpty());
	}
	
	@Test
	public void testChainDepositInMainChain() throws Exception {
		
		String chainId = MultiChainUtil.child0Id;
		String from = ALICE.getAddress();
		BigInteger amount= new BigInteger("10000000000000000000000");//10000*1e+18
		
		//ABIPack(pabi.DepositInMainChain.String(), chainId)
		Function function = new Function(
				"DepositInMainChain", 
				Arrays.asList(new Utf8String(chainId)),
				Collections.<TypeReference<?>>emptyList()
				);

		String data = FunctionEncoder.encode(function);
				
		BigInteger nonce = MultiChainUtil.GetNonce(MultiChainUtil.mainChainId, from);
		RawTransaction rawTransaction = RawTransaction.createTransaction(
	            nonce, MultiChainUtil.GAS_PRICE, MultiChainUtil.GAS_LIMIT, 
	            MultiChainUtil.ChainContractMagicAddr, amount, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, MultiChainUtil.mainChainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		Web3j web3j = MultiChainUtil.BuildWeb3j(MultiChainUtil.mainChainId);

		EthSendTransaction ethSendTransaction =
				web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }
	        
	    String transactionHash = ethSendTransaction.getTransactionHash();

		TransactionReceipt transactionReceipt =
				MultiChainUtil.WaitForTransactionReceipt(MultiChainUtil.mainChainId, transactionHash);
				
		assertFalse(transactionReceipt.toString().isEmpty());
	}
	
	@Test
	public void testChainDepositInChildChain() throws Exception {
		
		String chainId = MultiChainUtil.child0Id;
		String from = ALICE.getAddress();
		String txHash = "0xd71a66a375974b76718f41b14ba04ad4eebdc1a9b0264ec06708e2a7817258d2";
		
		// ABIPack(pabi.DepositInChildChain.String(), chainId, txHash)
		Function function = new Function("DepositInChildChain",
				Arrays.asList(new Utf8String(chainId), new Bytes32(Numeric.hexStringToByteArray(txHash))),
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = RawTransaction.createTransaction(
	            nonce, MultiChainUtil.GAS_PRICE, BigInteger.ZERO, 
	            MultiChainUtil.ChainContractMagicAddr, BigInteger.ZERO, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		Web3j web3j = MultiChainUtil.BuildWeb3j(chainId);

		EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }

	    String transactionHash = ethSendTransaction.getTransactionHash();

		TransactionReceipt transactionReceipt = MultiChainUtil.WaitForTransactionReceipt(chainId, transactionHash);

		assertFalse(transactionReceipt.toString().isEmpty());
	}
	
	@Test
	public void testChainWithdrawFromChildChain() throws Exception {
		
		String chainId = MultiChainUtil.child0Id;
		String from = ALICE.getAddress(); 
		BigInteger amount = new BigInteger("100000000000000000000"); //100*1e+18
		
		//ABIPack(WithdrawFromChildChain, chainId)
		Function function = new Function(
				"WithdrawFromChildChain", 
				Arrays.asList(new Utf8String(chainId)),
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
	public void testChainWithdrawFromMainChain() throws Exception{
		
		String from = ALICE.getAddress();
		String chainId = MultiChainUtil.child0Id;
		BigInteger amount = new BigInteger("1000000000000000000000");
		String txHash = "0xfea66bc7ceb4419e04ecc61d286c777399aab227b79d3b8f7db00508ee5763cc";
		//ABIPack(WithdrawFromMainChain, chainId, (*big.Int)(amount), txHash)
		Function function = new Function("WithdrawFromMainChain",
				Arrays.asList(new Utf8String(chainId), new Uint(amount),
						new Bytes32(Numeric.hexStringToByteArray(txHash))), 
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		BigInteger nonce = MultiChainUtil.GetNonce(MultiChainUtil.mainChainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
				nonce, MultiChainUtil.ChainContractMagicAddr, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, MultiChainUtil.mainChainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		Web3j web3j = MultiChainUtil.BuildWeb3j(MultiChainUtil.mainChainId);

		EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }
	        
	    String transactionHash = ethSendTransaction.getTransactionHash();
	    TransactionReceipt transactionReceipt = 
	        	MultiChainUtil.WaitForTransactionReceipt(MultiChainUtil.mainChainId, transactionHash);
		assertFalse(transactionReceipt.toString().isEmpty());	
	}

}
