package org.pweb3j.protocol.scenarios;

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
import org.pweb3j.crypto.Hash;
import org.pweb3j.crypto.RawTransaction;
import org.pweb3j.crypto.TransactionEncoder;
import org.pweb3j.protocol.Web3j;
import org.pweb3j.protocol.core.methods.response.EthSendTransaction;
import org.pweb3j.protocol.core.methods.response.TransactionReceipt;
import org.pweb3j.protocol.http.HttpService;
import org.pweb3j.utils.Numeric;
import static junit.framework.TestCase.assertFalse;

public class EpochFunctionIT extends Scenario {
	
	final BigInteger minimumVoteAmount = new BigInteger("100000000000000000000000"); //100000*1.0e+18
			
	final String from = ALICE.getAddress();
	final String chainId = MultiChainUtil.mainChainId;
	final String pubkey = "753F042FFDDF162BC9A6F78DE52EAD218BC529C6D4839FB2E6CFC64CB057A4742B169BA5BFD6EA5D38F5EC1B9FA9EC8A72F8CB8C38571219CCFD2EEAFAE5EA9E039E5E6D82ED2C0A13CC53A3F65163D5597438CC03F29C42BC1E7783B1853E8B0CBD03C3C96FD5106182AC0264FC9ABD6F901531DDF436991532D60EF83F282C";
	final BigInteger amount= minimumVoteAmount;
	final String salt = "1234";

	//signature is calculated by RPC chain_signAddress
	final String signature = "0x8189a1f9432649ef8708e76e00448168e177667ba16a68ae4650b1b3eab0ea4d154c58ccb4f422422a7db37911d90164271d4e8dd18683dde60286bed4adede1";
	
	@Test
	public void testTdmVoteNextEpoch() throws Exception {
		
		byte[] fromBytes = Numeric.hexStringToByteArray(from);
		byte[] pubkeyBytes = Numeric.hexStringToByteArray(pubkey);
		byte[] amountBytes = amount.toByteArray();
		byte[] saltBytes = salt.getBytes();
		byte[] totalBytes = new byte[fromBytes.length + pubkeyBytes.length + amountBytes.length + saltBytes.length];
		System.arraycopy(fromBytes, 0, totalBytes, 0, fromBytes.length);
		System.arraycopy(pubkeyBytes, 0, totalBytes, fromBytes.length, pubkeyBytes.length);
		System.arraycopy(amountBytes, 0, totalBytes, fromBytes.length + pubkeyBytes.length, amountBytes.length);
		System.arraycopy(saltBytes, 0, totalBytes, fromBytes.length + pubkeyBytes.length + amountBytes.length, saltBytes.length);
		
		String voteHash = Numeric.toHexString(Hash.sha3(totalBytes));
		
		// ABIPack(VoteNextEpoch, chainId, voteHash)
		Function function = new Function("VoteNextEpoch",
				Arrays.asList(new Bytes32(Numeric.hexStringToByteArray(voteHash))),
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
				nonce, MultiChainUtil.ChainContractMagicAddr, data);

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
	public void testTdmRevealVote() throws Exception {
		
		
		//ABIPack(pabi.RevealVote.String(), pubkey.Bytes(), (*big.Int)(amount), salt, signature)
		Function function = new Function("RevealVote",
				Arrays.asList(new DynamicBytes(Numeric.hexStringToByteArray(pubkey)), 
						new Uint(amount), 
						new Utf8String(salt), 
						new DynamicBytes(Numeric.hexStringToByteArray(signature))),
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(nonce, MultiChainUtil.ChainContractMagicAddr, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		Web3j web3 = MultiChainUtil.BuildWeb3j(chainId);

		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
		if(ethSendTransaction.hasError()) {
	        throw new Exception(ethSendTransaction.getError().getMessage());
	    }

	    String transactionHash = ethSendTransaction.getTransactionHash();
		TransactionReceipt transactionReceipt = 
				MultiChainUtil.WaitForTransactionReceipt(chainId, transactionHash);

		assertFalse(transactionReceipt.toString().isEmpty());
	}
}
