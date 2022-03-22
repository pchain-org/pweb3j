package org.pweb3j.protocol.core;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import io.reactivex.Flowable;

import org.junit.Test;
import org.pweb3j.protocol.Web3j;
import org.pweb3j.protocol.Web3jService;
import org.pweb3j.protocol.core.methods.request.ShhFilter;
import org.pweb3j.protocol.core.methods.request.ShhPost;
import org.pweb3j.protocol.core.methods.request.Transaction;
import org.pweb3j.protocol.core.methods.response.*;
import org.pweb3j.protocol.rx.JsonRpc2_0Rx;
import org.pweb3j.protocol.websocket.events.LogNotification;
import org.pweb3j.protocol.websocket.events.NewHeadsNotification;
import org.pweb3j.utils.Async;
import org.pweb3j.utils.Numeric;

import static junit.framework.TestCase.assertFalse;

/**
 * JSON-RPC 2.0 factory implementation.
 */
public class JsonRpc2_0Web3j implements Web3j {

    public static final int DEFAULT_BLOCK_TIME = 15 * 1000;

    protected final Web3jService web3jService;
    private final JsonRpc2_0Rx web3jRx;
    private final long blockTime;
    private final ScheduledExecutorService scheduledExecutorService;

    public JsonRpc2_0Web3j(Web3jService web3jService) {
        this(web3jService, DEFAULT_BLOCK_TIME, Async.defaultExecutorService());
    }

    public JsonRpc2_0Web3j(
            Web3jService web3jService, long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        this.web3jService = web3jService;
        this.web3jRx = new JsonRpc2_0Rx(this, scheduledExecutorService);
        this.blockTime = pollingInterval;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public Request<?, Web3ClientVersion> web3ClientVersion() {
        return new Request<>(
                "web3_clientVersion",
                Collections.<String>emptyList(),
                web3jService,
                Web3ClientVersion.class);
    }

    @Override
    public Request<?, Web3Sha3> web3Sha3(String data) {
        return new Request<>(
                "web3_sha3",
                Arrays.asList(data),
                web3jService,
                Web3Sha3.class);
    }

    @Override
    public Request<?, NetVersion> netVersion() {
        return new Request<>(
                "net_version",
                Collections.<String>emptyList(),
                web3jService,
                NetVersion.class);
    }

    @Override
    public Request<?, NetListening> netListening() {
        return new Request<>(
                "net_listening",
                Collections.<String>emptyList(),
                web3jService,
                NetListening.class);
    }

    @Override
    public Request<?, NetPeerCount> netPeerCount() {
        return new Request<>(
                "net_peerCount",
                Collections.<String>emptyList(),
                web3jService,
                NetPeerCount.class);
    }

    @Override
    public Request<?, EthProtocolVersion> ethProtocolVersion() {
        return new Request<>(
                "eth_protocolVersion",
                Collections.<String>emptyList(),
                web3jService,
                EthProtocolVersion.class);
    }

    @Override
    public Request<?, EthCoinbase> ethCoinbase() {
        return new Request<>(
                "eth_coinbase",
                Collections.<String>emptyList(),
                web3jService,
                EthCoinbase.class);
    }

    @Override
    public Request<?, EthSyncing> ethSyncing() {
        return new Request<>(
                "eth_syncing",
                Collections.<String>emptyList(),
                web3jService,
                EthSyncing.class);
    }

    @Override
    public Request<?, EthMining> ethMining() {
        return new Request<>(
                "eth_mining",
                Collections.<String>emptyList(),
                web3jService,
                EthMining.class);
    }

    @Override
    public Request<?, EthHashrate> ethHashrate() {
        return new Request<>(
                "eth_hashrate",
                Collections.<String>emptyList(),
                web3jService,
                EthHashrate.class);
    }

    @Override
    public Request<?, EthGasPrice> ethGasPrice() {
        return new Request<>(
                "eth_gasPrice",
                Collections.<String>emptyList(),
                web3jService,
                EthGasPrice.class);
    }

    @Override
    public Request<?, EthAccounts> ethAccounts() {
        return new Request<>(
                "eth_accounts",
                Collections.<String>emptyList(),
                web3jService,
                EthAccounts.class);
    }

    @Override
    public Request<?, EthBlockNumber> ethBlockNumber() {
        return new Request<>(
                "eth_blockNumber",
                Collections.<String>emptyList(),
                web3jService,
                EthBlockNumber.class);
    }

    @Override
    public Request<?, EthGetBalance> ethGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eth_getBalance",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                EthGetBalance.class);
    }

    @Override
    public Request<?, EthGetStorageAt> ethGetStorageAt(
            String address, BigInteger position, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eth_getStorageAt",
                Arrays.asList(
                        address,
                        Numeric.encodeQuantity(position),
                        defaultBlockParameter.getValue()),
                web3jService,
                EthGetStorageAt.class);
    }

    @Override
    public Request<?, EthGetTransactionCount> ethGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eth_getTransactionCount",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                EthGetTransactionCount.class);
    }

    @Override
    public Request<?, EthGetBlockTransactionCountByHash> ethGetBlockTransactionCountByHash(
            String blockHash) {
        return new Request<>(
                "eth_getBlockTransactionCountByHash",
                Arrays.asList(blockHash),
                web3jService,
                EthGetBlockTransactionCountByHash.class);
    }

    @Override
    public Request<?, EthGetBlockTransactionCountByNumber> ethGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eth_getBlockTransactionCountByNumber",
                Arrays.asList(defaultBlockParameter.getValue()),
                web3jService,
                EthGetBlockTransactionCountByNumber.class);
    }

    @Override
    public Request<?, EthGetUncleCountByBlockHash> ethGetUncleCountByBlockHash(String blockHash) {
        return new Request<>(
                "eth_getUncleCountByBlockHash",
                Arrays.asList(blockHash),
                web3jService,
                EthGetUncleCountByBlockHash.class);
    }

    @Override
    public Request<?, EthGetUncleCountByBlockNumber> ethGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eth_getUncleCountByBlockNumber",
                Arrays.asList(defaultBlockParameter.getValue()),
                web3jService,
                EthGetUncleCountByBlockNumber.class);
    }

    @Override
    public Request<?, EthGetCode> ethGetCode(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eth_getCode",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                EthGetCode.class);
    }

    @Override
    public Request<?, EthSign> ethSign(String address, String sha3HashOfDataToSign) {
        return new Request<>(
                "eth_sign",
                Arrays.asList(address, sha3HashOfDataToSign),
                web3jService,
                EthSign.class);
    }

    @Override
    public Request<?, org.pweb3j.protocol.core.methods.response.EthSendTransaction>
            ethSendTransaction(
            Transaction transaction) {
        return new Request<>(
                "eth_sendTransaction",
                Arrays.asList(transaction),
                web3jService,
                org.pweb3j.protocol.core.methods.response.EthSendTransaction.class);
    }

    @Override
    public Request<?, org.pweb3j.protocol.core.methods.response.EthSendTransaction>
            ethSendRawTransaction(
            String signedTransactionData) {
        return new Request<>(
                "eth_sendRawTransaction",
                Arrays.asList(signedTransactionData),
                web3jService,
                org.pweb3j.protocol.core.methods.response.EthSendTransaction.class);
    }

    @Override
    public Request<?, org.pweb3j.protocol.core.methods.response.EthCall> ethCall(
            Transaction transaction, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eth_call",
                Arrays.asList(transaction, defaultBlockParameter),
                web3jService,
                org.pweb3j.protocol.core.methods.response.EthCall.class);
    }

    @Override
    public Request<?, EthEstimateGas> ethEstimateGas(Transaction transaction) {
        return new Request<>(
                "eth_estimateGas",
                Arrays.asList(transaction),
                web3jService,
                EthEstimateGas.class);
    }

    @Override
    public Request<?, EthBlock> ethGetBlockByHash(
            String blockHash, boolean returnFullTransactionObjects) {
        return new Request<>(
                "eth_getBlockByHash",
                Arrays.asList(
                        blockHash,
                        returnFullTransactionObjects),
                web3jService,
                EthBlock.class);
    }

    @Override
    public Request<?, EthBlock> ethGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects) {
        return new Request<>(
                "eth_getBlockByNumber",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        returnFullTransactionObjects),
                web3jService,
                EthBlock.class);
    }

    @Override
    public Request<?, EthTransaction> ethGetTransactionByHash(String transactionHash) {
        return new Request<>(
                "eth_getTransactionByHash",
                Arrays.asList(transactionHash),
                web3jService,
                EthTransaction.class);
    }

    @Override
    public Request<?, EthTransaction> ethGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex) {
        return new Request<>(
                "eth_getTransactionByBlockHashAndIndex",
                Arrays.asList(
                        blockHash,
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                EthTransaction.class);
    }

    @Override
    public Request<?, EthTransaction> ethGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex) {
        return new Request<>(
                "eth_getTransactionByBlockNumberAndIndex",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                EthTransaction.class);
    }

    @Override
    public Request<?, EthGetTransactionReceipt> ethGetTransactionReceipt(String transactionHash) {
        return new Request<>(
                "eth_getTransactionReceipt",
                Arrays.asList(transactionHash),
                web3jService,
                EthGetTransactionReceipt.class);
    }

    @Override
    public Request<?, EthBlock> ethGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex) {
        return new Request<>(
                "eth_getUncleByBlockHashAndIndex",
                Arrays.asList(
                        blockHash,
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                EthBlock.class);
    }

    @Override
    public Request<?, EthBlock> ethGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger uncleIndex) {
        return new Request<>(
                "eth_getUncleByBlockNumberAndIndex",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        Numeric.encodeQuantity(uncleIndex)),
                web3jService,
                EthBlock.class);
    }

    @Override
    public Request<?, EthGetCompilers> ethGetCompilers() {
        return new Request<>(
                "eth_getCompilers",
                Collections.<String>emptyList(),
                web3jService,
                EthGetCompilers.class);
    }

    @Override
    public Request<?, EthCompileLLL> ethCompileLLL(String sourceCode) {
        return new Request<>(
                "eth_compileLLL",
                Arrays.asList(sourceCode),
                web3jService,
                EthCompileLLL.class);
    }

    @Override
    public Request<?, EthCompileSolidity> ethCompileSolidity(String sourceCode) {
        return new Request<>(
                "eth_compileSolidity",
                Arrays.asList(sourceCode),
                web3jService,
                EthCompileSolidity.class);
    }

    @Override
    public Request<?, EthCompileSerpent> ethCompileSerpent(String sourceCode) {
        return new Request<>(
                "eth_compileSerpent",
                Arrays.asList(sourceCode),
                web3jService,
                EthCompileSerpent.class);
    }

    @Override
    public Request<?, EthFilter> ethNewFilter(
            org.pweb3j.protocol.core.methods.request.EthFilter ethFilter) {
        return new Request<>(
                "eth_newFilter",
                Arrays.asList(ethFilter),
                web3jService,
                EthFilter.class);
    }

    @Override
    public Request<?, EthFilter> ethNewBlockFilter() {
        return new Request<>(
                "eth_newBlockFilter",
                Collections.<String>emptyList(),
                web3jService,
                EthFilter.class);
    }

    @Override
    public Request<?, EthFilter> ethNewPendingTransactionFilter() {
        return new Request<>(
                "eth_newPendingTransactionFilter",
                Collections.<String>emptyList(),
                web3jService,
                EthFilter.class);
    }

    @Override
    public Request<?, EthUninstallFilter> ethUninstallFilter(BigInteger filterId) {
        return new Request<>(
                "eth_uninstallFilter",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                EthUninstallFilter.class);
    }

    @Override
    public Request<?, EthLog> ethGetFilterChanges(BigInteger filterId) {
        return new Request<>(
                "eth_getFilterChanges",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                EthLog.class);
    }

    @Override
    public Request<?, EthLog> ethGetFilterLogs(BigInteger filterId) {
        return new Request<>(
                "eth_getFilterLogs",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                EthLog.class);
    }

    @Override
    public Request<?, EthLog> ethGetLogs(
            org.pweb3j.protocol.core.methods.request.EthFilter ethFilter) {
        return new Request<>(
                "eth_getLogs",
                Arrays.asList(ethFilter),
                web3jService,
                EthLog.class);
    }

    @Override
    public Request<?, EthGetWork> ethGetWork() {
        return new Request<>(
                "eth_getWork",
                Collections.<String>emptyList(),
                web3jService,
                EthGetWork.class);
    }

    @Override
    public Request<?, EthSubmitWork> ethSubmitWork(
            String nonce, String headerPowHash, String mixDigest) {
        return new Request<>(
                "eth_submitWork",
                Arrays.asList(nonce, headerPowHash, mixDigest),
                web3jService,
                EthSubmitWork.class);
    }

    @Override
    public Request<?, EthSubmitHashrate> ethSubmitHashrate(String hashrate, String clientId) {
        return new Request<>(
                "eth_submitHashrate",
                Arrays.asList(hashrate, clientId),
                web3jService,
                EthSubmitHashrate.class);
    }

    @Override
    public Request<?, EthGetFullBalance> ethGetFullBalance(String from,
                                                           DefaultBlockParameter blockNumber, boolean fullDetail) {

        return new Request<>(
                "eth_getFullBalance",
                Arrays.asList(from, blockNumber.getValue(), fullDetail),
                web3jService,
                EthGetFullBalance.class);
    }

    @Override
    public Request<?, EthChainId> ethChainId() {
        return new Request<>(
                "eth_chainId",
                Collections.<String>emptyList(),
                web3jService,
                EthChainId.class);
    }

    @Override
    public Request<?, DbPutString> dbPutString(
            String databaseName, String keyName, String stringToStore) {
        return new Request<>(
                "db_putString",
                Arrays.asList(databaseName, keyName, stringToStore),
                web3jService,
                DbPutString.class);
    }

    @Override
    public Request<?, DbGetString> dbGetString(String databaseName, String keyName) {
        return new Request<>(
                "db_getString",
                Arrays.asList(databaseName, keyName),
                web3jService,
                DbGetString.class);
    }

    @Override
    public Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore) {
        return new Request<>(
                "db_putHex",
                Arrays.asList(databaseName, keyName, dataToStore),
                web3jService,
                DbPutHex.class);
    }

    @Override
    public Request<?, DbGetHex> dbGetHex(String databaseName, String keyName) {
        return new Request<>(
                "db_getHex",
                Arrays.asList(databaseName, keyName),
                web3jService,
                DbGetHex.class);
    }

    @Override
    public Request<?, org.pweb3j.protocol.core.methods.response.ShhPost> shhPost(ShhPost shhPost) {
        return new Request<>(
                "shh_post",
                Arrays.asList(shhPost),
                web3jService,
                org.pweb3j.protocol.core.methods.response.ShhPost.class);
    }

    @Override
    public Request<?, ShhVersion> shhVersion() {
        return new Request<>(
                "shh_version",
                Collections.<String>emptyList(),
                web3jService,
                ShhVersion.class);
    }

    @Override
    public Request<?, ShhNewIdentity> shhNewIdentity() {
        return new Request<>(
                "shh_newIdentity",
                Collections.<String>emptyList(),
                web3jService,
                ShhNewIdentity.class);
    }

    @Override
    public Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress) {
        return new Request<>(
                "shh_hasIdentity",
                Arrays.asList(identityAddress),
                web3jService,
                ShhHasIdentity.class);
    }

    @Override
    public Request<?, ShhNewGroup> shhNewGroup() {
        return new Request<>(
                "shh_newGroup",
                Collections.<String>emptyList(),
                web3jService,
                ShhNewGroup.class);
    }

    @Override
    public Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress) {
        return new Request<>(
                "shh_addToGroup",
                Arrays.asList(identityAddress),
                web3jService,
                ShhAddToGroup.class);
    }

    @Override
    public Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter) {
        return new Request<>(
                "shh_newFilter",
                Arrays.asList(shhFilter),
                web3jService,
                ShhNewFilter.class);
    }

    @Override
    public Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId) {
        return new Request<>(
                "shh_uninstallFilter",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhUninstallFilter.class);
    }

    @Override
    public Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId) {
        return new Request<>(
                "shh_getFilterChanges",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhMessages.class);
    }

    @Override
    public Request<?, ShhMessages> shhGetMessages(BigInteger filterId) {
        return new Request<>(
                "shh_getMessages",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhMessages.class);
    }

    @Override
    public Flowable<NewHeadsNotification> newHeadsNotifications() {
        return web3jService.subscribe(
                new Request<>(
                        "eth_subscribe",
                        Collections.singletonList("newHeads"),
                        web3jService,
                        EthSubscribe.class),
                "eth_unsubscribe",
                NewHeadsNotification.class
        );
    }

    @Override
    public Flowable<LogNotification> logsNotifications(
            List<String> addresses, List<String> topics) {

        Map<String, Object> params = createLogsParams(addresses, topics);

        return web3jService.subscribe(
                new Request<>(
                        "eth_subscribe",
                        Arrays.asList("logs", params),
                        web3jService,
                        EthSubscribe.class),
                "eth_unsubscribe",
                LogNotification.class
        );
    }

    private Map<String, Object> createLogsParams(List<String> addresses, List<String> topics) {
        Map<String, Object> params = new HashMap<>();
        if (!addresses.isEmpty()) {
            params.put("address", addresses);
        }
        if (!topics.isEmpty()) {
            params.put("topics", topics);
        }
        return params;
    }

    @Override
    public Flowable<String> ethBlockHashFlowable() {
        return web3jRx.ethBlockHashFlowable(blockTime);
    }

    @Override
    public Flowable<String> ethPendingTransactionHashFlowable() {
        return web3jRx.ethPendingTransactionHashFlowable(blockTime);
    }

    @Override
    public Flowable<Log> ethLogFlowable(
            org.pweb3j.protocol.core.methods.request.EthFilter ethFilter) {
        return web3jRx.ethLogFlowable(ethFilter, blockTime);
    }

    @Override
    public Flowable<org.pweb3j.protocol.core.methods.response.Transaction>
            transactionFlowable() {
        return web3jRx.transactionFlowable(blockTime);
    }

    @Override
    public Flowable<org.pweb3j.protocol.core.methods.response.Transaction>
            pendingTransactionFlowable() {
        return web3jRx.pendingTransactionFlowable(blockTime);
    }

    @Override
    public Flowable<EthBlock> blockFlowable(boolean fullTransactionObjects) {
        return web3jRx.blockFlowable(fullTransactionObjects, blockTime);
    }

    @Override
    public Flowable<EthBlock> replayPastBlocksFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock,
            boolean fullTransactionObjects) {
        return web3jRx.replayBlocksFlowable(startBlock, endBlock, fullTransactionObjects);
    }

    @Override
    public Flowable<EthBlock> replayPastBlocksFlowable(DefaultBlockParameter startBlock,
                                                      DefaultBlockParameter endBlock,
                                                      boolean fullTransactionObjects,
                                                      boolean ascending) {
        return web3jRx.replayBlocksFlowable(startBlock, endBlock,
                fullTransactionObjects, ascending);
    }

    @Override
    public Flowable<EthBlock> replayPastBlocksFlowable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects,
            Flowable<EthBlock> onCompleteFlowable) {
        return web3jRx.replayPastBlocksFlowable(
                startBlock, fullTransactionObjects, onCompleteFlowable);
    }

    @Override
    public Flowable<EthBlock> replayPastBlocksFlowable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return web3jRx.replayPastBlocksFlowable(startBlock, fullTransactionObjects);
    }

    @Override
    public Flowable<org.pweb3j.protocol.core.methods.response.Transaction>
            replayPastTransactionsFlowable(DefaultBlockParameter startBlock,
                                          DefaultBlockParameter endBlock) {
        return web3jRx.replayTransactionsFlowable(startBlock, endBlock);
    }

    @Override
    public Flowable<org.pweb3j.protocol.core.methods.response.Transaction>
            replayPastTransactionsFlowable(DefaultBlockParameter startBlock) {
        return web3jRx.replayPastTransactionsFlowable(startBlock);
    }

    @Override
    public Flowable<EthBlock> replayPastAndFutureBlocksFlowable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return web3jRx.replayPastAndFutureBlocksFlowable(
                startBlock, fullTransactionObjects, blockTime);
    }

    @Override
    public Flowable<org.pweb3j.protocol.core.methods.response.Transaction>
            replayPastAndFutureTransactionsFlowable(DefaultBlockParameter startBlock) {
        return web3jRx.replayPastAndFutureTransactionsFlowable(
                startBlock, blockTime);
    }

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
        try {
            web3jService.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to close web3j service", e);
        }
    }
    
    @Override
    public Request<?, ChainCreateChildChain> chainCreateChildChain(String from, String chainId,
    		String minValidators, String minDepositAmount, String startBlock, String endBlock, 
    		String gasPrice) {

    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, chainId, minValidators, minDepositAmount, 
    				startBlock, endBlock, gasPrice);
    	} else {
    		params = Arrays.asList(from, chainId, minValidators, minDepositAmount, 
    				startBlock, endBlock);
    	}
    	
    	return new Request<>(
                "chain_createChildChain",
                params,
                web3jService,
                ChainCreateChildChain.class);
    }
    
    @Override
    public Request<?, ChainJoinChildChain> chainJoinChildChain(String from, String pubkey,
    		String chainId, String depositAmount, String signature, String gasPrice) {
    	
    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, pubkey, chainId, depositAmount, 
    				signature, gasPrice);
    	} else {
    		params = Arrays.asList(from, pubkey, chainId, depositAmount, signature);
    	}
    	
    	return new Request<>(
                "chain_joinChildChain",
                params,
                web3jService,
                ChainJoinChildChain.class);
    }
    
    @Override
    public Request<?, ChainDepositInMainChain> chainDepositInMainChain(String from, String chainId, 
    		String amount, String gasPrice) {
    	
    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, chainId, amount, gasPrice);
    	} else {
    		params = Arrays.asList(from, chainId, amount);
    	}
    	
    	return new Request<>(
                "chain_depositInMainChain",
                params,
                web3jService,
                ChainDepositInMainChain.class);
    }
    
    @Override
    public Request<?, ChainDepositInChildChain> chainDepositInChildChain(String from, String txHash) {
    	
    	return new Request<>(
                "chain_depositInChildChain",
                Arrays.asList(from, txHash),
                web3jService,
                ChainDepositInChildChain.class);
    }
    
    @Override
    public Request<?, ChainWithdrawFromChildChain> chainWithdrawFromChildChain(String from, 
    		String amount, String gasPrice) {
    	
    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, amount, gasPrice);
    	} else {
    		params = Arrays.asList(from, amount);
    	}
    	
    	return new Request<>(
                "chain_withdrawFromChildChain",
                params,
                web3jService,
                ChainWithdrawFromChildChain.class);
    }
    
    @Override
    public Request<?, ChainWithdrawFromMainChain> chainWithdrawFromMainChain(String from, 
    		String amount, String chainId, String txHash) {
    	
    	return new Request<>(
                "chain_withdrawFromMainChain",
                Arrays.asList(from, amount, chainId, txHash),
                web3jService,
                ChainWithdrawFromMainChain.class);
    }
    
    @Override
    public Request<?, ChainSignAddress> chainSignAddress(String from, String privateKey) {
    	
    	return new Request<>(
                "chain_signAddress",
                Arrays.asList(from, privateKey),
                web3jService,
                ChainSignAddress.class);
    }
    
    @Override
    public Request<?, ChainSetBlockReward> chainSetBlockReward(String from, String reward) {
    	
    	return new Request<>(
                "chain_setBlockReward",
                Arrays.asList(from, reward),
                web3jService,
                ChainSetBlockReward.class);
    }
    
    @Override
    public Request<?, ChainGetBlockReward> chainGetBlockReward(DefaultBlockParameter blockNumber) {
    	
    	return new Request<>(
                "chain_getBlockReward",
                Arrays.asList(blockNumber),
                web3jService,
                ChainGetBlockReward.class);
    }
    
    @Override
    public Request<?, ChainGetAllChains> chainGetAllChains() {
    	
    	return new Request<>(
                "chain_getAllChains",
                Collections.<String>emptyList(),
                web3jService,
                ChainGetAllChains.class);
    }
	
    @Override
    public Request<?, TdmVoteNextEpoch> tdmVoteNextEpoch(
    		String from, 
    		String voteHash, 
    		String gasPrice) {
    	
    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, voteHash, gasPrice);
    	} else {
    		params = Arrays.asList(from, voteHash);
    	}
    	
    	return new Request<>(
                "tdm_voteNextEpoch",
                params,
                web3jService,
                TdmVoteNextEpoch.class);
    }
    
    @Override
	public Request<?, TdmRevealVote> tdmRevealVote(String from, String pubkey, String amount,
    		String salt, String signature, String gasPrice) {
    	
    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, pubkey, amount, salt, signature, gasPrice);
    	} else {
    		params = Arrays.asList(from, pubkey, amount, salt, signature);
    	}
    	
    	return new Request<>(
                "tdm_revealVote",
                params,
                web3jService,
                TdmRevealVote.class);
    }
    
    @Override
    public Request<?, TdmGetCurrentEpochNumber> tdmGetCurrentEpochNumber() {
    	return new Request<>(
                "tdm_getCurrentEpochNumber",
                Collections.<String>emptyList(),
                web3jService,
                TdmGetCurrentEpochNumber.class);
    }
    
    @Override
	public Request<?, TdmGetEpoch> tdmGetEpoch(int number) {
		return new Request<>(
                "tdm_getEpoch",
                Arrays.asList(String.format("0x%x", number)),
                web3jService,
                TdmGetEpoch.class);
	}

    @Override
    public Request<?, TdmGetCurrentEpochNumber> tdmGetCurrentEpochNumberOfChildChain(String chainId) {
        return new Request<>(
                "tdm_getCurrentEpochNumberOfChildChain",
                Arrays.asList(chainId),
                web3jService,
                TdmGetCurrentEpochNumber.class);
    }

    @Override
    public Request<?, TdmGetEpoch> tdmGetEpochOfChildChain(String chainId, int number) {
        return new Request<>(
                "tdm_getEpochOfChildChain",
                Arrays.asList(chainId, String.format("0x%x", number)),
                web3jService,
                TdmGetEpoch.class);
    }
    
    @Override
    public Request<?, TdmGetNextEpochVote> tdmGetNextEpochVote() {
    	return new Request<>(
                "tdm_getNextEpochVote",
                Collections.<String>emptyList(),
                web3jService,
                TdmGetNextEpochVote.class);
    }
    
    @Override
	public Request<?, TdmGetNextEpochValidators> tdmGetNextEpochValidators() {
    	return new Request<>(
                "tdm_getNextEpochValidators",
                Collections.<String>emptyList(),
                web3jService,
                TdmGetNextEpochValidators.class);
    }
    
    @Override
	public Request<?, TdmGeneratePrivateValidator> tdmGeneratePrivateValidator(String address) {
    	return new Request<>(
                "tdm_generatePrivateValidator",
                Arrays.asList(address),
                web3jService,
                TdmGeneratePrivateValidator.class);
    }

    @Override
    public Request<?, TdmPeers> tdmPeers() {
        return new Request<>(
                "tdm_peers",
                Collections.<String>emptyList(),
                web3jService,
                TdmPeers.class);
    }
    
    @Override
	public Request<?, DelDelegate> delDelegate(String from, String candidate, 
    		String amount, String gasPrice) {

    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, candidate, amount, gasPrice);
    	} else {
    		params = Arrays.asList(from, candidate, amount);
    	}
    	
    	return new Request<>(
                "del_delegate",
                params,
                web3jService,
                DelDelegate.class);
    }

    @Override
    public Request<?, DelCancelDelegate> delCancelDelegate(String from, String candidate, 
    		String amount, String gasPrice) {
    	
    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, candidate, amount, gasPrice);
    	} else {
    		params = Arrays.asList(from, candidate, amount);
    	}
    	
    	return new Request<>(
                "del_cancelDelegate",
                params,
                web3jService,
                DelCancelDelegate.class);
    }
    
    @Override
    public Request<?, DelApplyCandidate> delApplyCandidate(String from, String securityDeposit, 
    		int commission, String gasPrice) {
    	
    	List<? extends Object> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, securityDeposit, commission, gasPrice);
    	} else {
    		params = Arrays.asList(from, securityDeposit, commission);
    	}
    	
    	return new Request<>(
                "del_applyCandidate",
                params,
                web3jService,
                DelApplyCandidate.class);
    }
    
    @Override
    public Request<?, DelCancelCandidate> delCancelCandidate(String from, String gasPrice) {

    	List<String> params = null;
    	
    	if (gasPrice != null && !gasPrice.isEmpty()) {
    		params = Arrays.asList(from, gasPrice);
    	} else {
    		params = Arrays.asList(from);
    	}
    	
    	return new Request<>(
                "del_cancelCandidate",
                params,
                web3jService,
                DelCancelCandidate.class);
    }
    
    @Override
    public Request<?, DelCheckCandidate> delCheckCandidate(String from, DefaultBlockParameter blockNumber) {

    	return new Request<>(
                "del_checkCandidate",
                Arrays.asList(from, blockNumber.getValue()),
                web3jService,
                DelCheckCandidate.class);
    }

    @Override
    public Request<?, DelExtractReward> delExtractReward(String from, String gasPrice) {

        List<String> params = null;

        if (gasPrice != null && !gasPrice.isEmpty()) {
            params = Arrays.asList(from, gasPrice);
        } else {
            params = Arrays.asList(from);
        }

        return new Request<>(
                "del_extractReward",
                params,
                web3jService,
                DelExtractReward.class);
    }

}
