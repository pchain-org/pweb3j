package org.pweb3j.protocol.core;

import java.math.BigInteger;

import org.pweb3j.protocol.core.methods.request.ShhFilter;
import org.pweb3j.protocol.core.methods.response.*;

/**
 * Core Ethereum JSON-RPC API.
 */
public interface Ethereum {
    Request<?, Web3ClientVersion> web3ClientVersion();

    Request<?, Web3Sha3> web3Sha3(String data);

    Request<?, NetVersion> netVersion();

    Request<?, NetListening> netListening();

    Request<?, NetPeerCount> netPeerCount();

    Request<?, EthProtocolVersion> ethProtocolVersion();

    Request<?, EthCoinbase> ethCoinbase();

    Request<?, EthSyncing> ethSyncing();

    Request<?, EthMining> ethMining();

    Request<?, EthHashrate> ethHashrate();

    Request<?, EthGasPrice> ethGasPrice();

    Request<?, EthAccounts> ethAccounts();

    Request<?, EthBlockNumber> ethBlockNumber();

    Request<?, EthGetBalance> ethGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetStorageAt> ethGetStorageAt(
            String address, BigInteger position,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetTransactionCount> ethGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetBlockTransactionCountByHash> ethGetBlockTransactionCountByHash(
            String blockHash);

    Request<?, EthGetBlockTransactionCountByNumber> ethGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetUncleCountByBlockHash> ethGetUncleCountByBlockHash(String blockHash);

    Request<?, EthGetUncleCountByBlockNumber> ethGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthGetCode> ethGetCode(String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EthSign> ethSign(String address, String sha3HashOfDataToSign);

    Request<?, org.pweb3j.protocol.core.methods.response.EthSendTransaction> ethSendTransaction(
            org.pweb3j.protocol.core.methods.request.Transaction transaction);

    Request<?, org.pweb3j.protocol.core.methods.response.EthSendTransaction> ethSendRawTransaction(
            String signedTransactionData);

    Request<?, org.pweb3j.protocol.core.methods.response.EthCall> ethCall(
            org.pweb3j.protocol.core.methods.request.Transaction transaction,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EthEstimateGas> ethEstimateGas(
            org.pweb3j.protocol.core.methods.request.Transaction transaction);

    Request<?, EthBlock> ethGetBlockByHash(String blockHash, boolean returnFullTransactionObjects);

    Request<?, EthBlock> ethGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects);

    Request<?, EthTransaction> ethGetTransactionByHash(String transactionHash);

    Request<?, EthTransaction> ethGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, EthTransaction> ethGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, EthGetTransactionReceipt> ethGetTransactionReceipt(String transactionHash);

    Request<?, EthBlock> ethGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, EthBlock> ethGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, EthGetCompilers> ethGetCompilers();

    Request<?, EthCompileLLL> ethCompileLLL(String sourceCode);

    Request<?, EthCompileSolidity> ethCompileSolidity(String sourceCode);

    Request<?, EthCompileSerpent> ethCompileSerpent(String sourceCode);

    Request<?, EthFilter> ethNewFilter(org.pweb3j.protocol.core.methods.request.EthFilter ethFilter);

    Request<?, EthFilter> ethNewBlockFilter();

    Request<?, EthFilter> ethNewPendingTransactionFilter();

    Request<?, EthUninstallFilter> ethUninstallFilter(BigInteger filterId);

    Request<?, EthLog> ethGetFilterChanges(BigInteger filterId);

    Request<?, EthLog> ethGetFilterLogs(BigInteger filterId);

    Request<?, EthLog> ethGetLogs(org.pweb3j.protocol.core.methods.request.EthFilter ethFilter);

    Request<?, EthGetWork> ethGetWork();

    Request<?, EthSubmitWork> ethSubmitWork(String nonce, String headerPowHash, String mixDigest);

    Request<?, EthSubmitHashrate> ethSubmitHashrate(String hashrate, String clientId);

    Request<?, EthGetFullBalance> ethGetFullBalance(String from,
                                                    DefaultBlockParameter blockNumber, boolean fullDetail);

    Request<?, EthChainId> ethChainId();

    Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore);

    Request<?, DbGetString> dbGetString(String databaseName, String keyName);

    Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore);

    Request<?, DbGetHex> dbGetHex(String databaseName, String keyName);

    Request<?, org.pweb3j.protocol.core.methods.response.ShhPost> shhPost(
            org.pweb3j.protocol.core.methods.request.ShhPost shhPost);

    Request<?, ShhVersion> shhVersion();

    Request<?, ShhNewIdentity> shhNewIdentity();

    Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress);

    Request<?, ShhNewGroup> shhNewGroup();

    Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress);

    Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter);

    Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId);

    Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId);

    Request<?, ShhMessages> shhGetMessages(BigInteger filterId);
    
    Request<?, ChainCreateChildChain> chainCreateChildChain(String from, String chainId,
    		String minValidators, String minDepositAmount, String startBlock, String endBlock, 
    		String gasPrice);
    
    Request<?, ChainJoinChildChain> chainJoinChildChain(String from, String pubkey,
    		String chainId, String depositAmount, String signature, String gasPrice);
    
    Request<?, ChainDepositInMainChain> chainDepositInMainChain(String from, String chainId, 
    		String amount, String gasPrice);
    
    Request<?, ChainDepositInChildChain> chainDepositInChildChain(String from, String txHash);
    
    Request<?, ChainWithdrawFromChildChain> chainWithdrawFromChildChain(String from, 
    		String amount, String gasPrice);
    
    Request<?, ChainWithdrawFromMainChain> chainWithdrawFromMainChain(String from, 
    		String amount, String chainId, String txHash);
    
    Request<?, ChainSignAddress> chainSignAddress(String from, String privateKey);
    
    Request<?, ChainSetBlockReward> chainSetBlockReward(String from, String reward);
    
    Request<?, ChainGetBlockReward> chainGetBlockReward(DefaultBlockParameter blockNumber);
    
    Request<?, ChainGetAllChains> chainGetAllChains();
    
    Request<?, TdmVoteNextEpoch> tdmVoteNextEpoch(String from, String voteHash, String gasPrice);
    
    Request<?, TdmRevealVote> tdmRevealVote(String from, String pubkey, String amount,
    		String salt, String signature, String gasPrice);
    
    Request<?, TdmGetCurrentEpochNumber> tdmGetCurrentEpochNumber();
    
    Request<?, TdmGetEpoch> tdmGetEpoch(int number);

    Request<?, TdmGetCurrentEpochNumber> tdmGetCurrentEpochNumberOfChildChain(String chainId);

    Request<?, TdmGetEpoch> tdmGetEpochOfChildChain(String chainId, int number);
    
    Request<?, TdmGetNextEpochVote> tdmGetNextEpochVote();
    
    Request<?, TdmGetNextEpochValidators> tdmGetNextEpochValidators();
    
    Request<?, TdmGeneratePrivateValidator> tdmGeneratePrivateValidator(String address);

    Request<?, TdmPeers> tdmPeers();

    Request<?, DelDelegate> delDelegate(String from, String candidate, 
    		String amount, String gasPrice);

    Request<?, DelCancelDelegate> delCancelDelegate(String from, String candidate, 
    		String amount, String gasPrice);
    
    Request<?, DelApplyCandidate> delApplyCandidate(String from, String securityDeposit, 
    		int commission, String gasPrice);
    
    Request<?, DelCancelCandidate> delCancelCandidate(String from, String gasPrice);
    
    Request<?, DelCheckCandidate> delCheckCandidate(String from, DefaultBlockParameter blockNumber);

    Request<?, DelExtractReward> delExtractReward(String from, String gasPrice);
}
