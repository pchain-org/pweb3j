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
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

public class ChainFunctionIT extends Scenario {
	

	@Test
    public void testChainCreateChildChain() throws Exception {

		String from = ALICE.getAddress();
		String chainId = MultiChainUtil.mainChainId;
		
		BigInteger blockNumber = MultiChainUtil.EthBlockNumber(chainId);
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
	public void testChainDepositInMainChain() throws Exception {
		
		String chainId = MultiChainUtil.child0Id;
		String from = ALICE.getAddress();
		BigInteger depositAmount= new BigInteger("10000000000000000000000");
		
		//ABIPack(pabi.DepositInMainChain.String(), chainId, (*big.Int)(amount))
		Function function = new Function(
				"DepositInMainChain", 
				Arrays.asList(new Utf8String(chainId), new Uint(depositAmount)),
				Collections.<TypeReference<?>>emptyList()
				);

		String data = FunctionEncoder.encode(function);
				
		BigInteger nonce = MultiChainUtil.GetNonce(MultiChainUtil.mainChainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(nonce, MultiChainUtil.ChainContractMagicAddr, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, MultiChainUtil.mainChainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		HttpService hs = new HttpService(MultiChainUtil.node + MultiChainUtil.mainChainId);
		Web3j web3 = Web3j.build(hs);

		EthSendTransaction ethSendTransaction =
				web3.ethSendRawTransaction(hexValue).sendAsync().get();
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
		String txHash = "0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fdd";
		
		// ABIPack(pabi.DepositInChildChain.String(), chainId, txHash)
		Function function = new Function("DepositInChildChain",
				Arrays.asList(new Utf8String(chainId), new Utf8String(txHash)),
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(nonce, 
				MultiChainUtil.ChainContractMagicAddr, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		Web3j web3j = Web3j.build(hs);

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
		BigInteger amount = new BigInteger("1000000000000000000000");
		
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

		HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		Web3j web3 = Web3j.build(hs);

		EthSendTransaction ethSendTransaction =
				web3.ethSendRawTransaction(hexValue).sendAsync().get();
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
		String txHash = "0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fdd";
		
		//ABIPack(WithdrawFromMainChain, chainId, (*big.Int)(amount), txHash)
		Function function = new Function("WithdrawFromMainChain",
				Arrays.asList(new Utf8String(chainId), new Uint(amount),
						new Utf8String(txHash)), 
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		BigInteger nonce = MultiChainUtil.GetNonce(MultiChainUtil.mainChainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
				nonce, MultiChainUtil.ChainContractMagicAddr, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, MultiChainUtil.mainChainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		HttpService hs = new HttpService(MultiChainUtil.node + MultiChainUtil.mainChainId);
		Web3j web3 = Web3j.build(hs);

		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
		if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }
	        
	    String transactionHash = ethSendTransaction.getTransactionHash();
	    TransactionReceipt transactionReceipt = 
	        	MultiChainUtil.WaitForTransactionReceipt(MultiChainUtil.mainChainId, transactionHash);
		assertFalse(transactionReceipt.toString().isEmpty());	
	}

}
