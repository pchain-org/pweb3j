package org.web3j.protocol.scenarios;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;
import static junit.framework.TestCase.assertFalse;

public class EpochFunctionIT extends Scenario {

	@Test
	public void testTdmVoteNextEpoch() throws Exception {
		
		String from = ALICE.getAddress();
		String chainId = MultiChainUtil.mainChainId;
		String voteHash = "0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fdf";
		
		// ABIPack(VoteNextEpoch, chainId, voteHash)
		Function function = new Function("VoteNextEpoch",
				Arrays.asList(new Utf8String(chainId), new Utf8String(voteHash)),
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		
		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(
				nonce, MultiChainUtil.ChainContractMagicAddr, data);

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
	public void testTdmRevealVote() throws Exception {
		
		String from = ALICE.getAddress();
		String chainId = MultiChainUtil.mainChainId;
		String pubkey = "0A2C9F325874BBC248D9DE013447829B8A7F964DC12EE841C7F551EB87C43A9A5ED25F4B676291A7D5A56EE75054BFFD0E8A724CC635723817AFE946BC116DFB035E8C7ED77AA6420801AABB91B6E2E0D9C32EFF0FD254DD9598684261FE467E799805E6545D5F22D5334E7677AAFDF561AD634DB941E1DC40E0474F5D1F0FB6";
		BigInteger amount= new BigInteger("10000000000000000000000");
		String salt = "1234";
		String signature = "0x2c6401216c9031b9a6fb8cbfccab4fcec6c951cdf40e2320108d1856eb532250576865fbcd452bcdc4c57321b619ed7a9cfd38bd973c3e1e0243ac2777fe9d5b";
		
		//ABIPack(pabi.RevealVote.String(), pubkey.Bytes(), (*big.Int)(amount), salt, signature)
		Function function = new Function("RevealVote",
				Arrays.asList(new Utf8String(pubkey), new Uint(amount), 
						new Utf8String(salt), new Utf8String(signature)),
				Collections.<TypeReference<?>>emptyList());

		String data = FunctionEncoder.encode(function);

		BigInteger nonce = MultiChainUtil.GetNonce(chainId, from);
		RawTransaction rawTransaction = MultiChainUtil.CreateTransaction(nonce, MultiChainUtil.ChainContractMagicAddr, data);

		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, ALICE);
		String hexValue = Numeric.toHexString(signedMessage);

		HttpService hs = new HttpService(MultiChainUtil.node + chainId);
		Web3j web3 = Web3j.build(hs);

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
