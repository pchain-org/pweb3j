package org.web3j.protocol.core;

import java.math.BigInteger;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.ChainCreateChildChain;
import org.web3j.protocol.core.methods.response.ChainDepositInChildChain;
import org.web3j.protocol.core.methods.response.ChainDepositInMainChain;
import org.web3j.protocol.core.methods.response.ChainGetAllChains;
import org.web3j.protocol.core.methods.response.ChainGetBlockReward;
import org.web3j.protocol.core.methods.response.ChainJoinChildChain;
import org.web3j.protocol.core.methods.response.ChainSetBlockReward;
import org.web3j.protocol.core.methods.response.ChainSignAddress;
import org.web3j.protocol.core.methods.response.ChainWithdrawFromChildChain;
import org.web3j.protocol.core.methods.response.ChainWithdrawFromMainChain;
import org.web3j.protocol.core.methods.response.DelApplyCandidate;
import org.web3j.protocol.core.methods.response.DelCancelCandidate;
import org.web3j.protocol.core.methods.response.DelCancelDelegate;
import org.web3j.protocol.core.methods.response.DelCheckCandidate;
import org.web3j.protocol.core.methods.response.DelDelegate;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthCompileLLL;
import org.web3j.protocol.core.methods.response.EthCompileSerpent;
import org.web3j.protocol.core.methods.response.EthCompileSolidity;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthFilter;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.EthGetCompilers;
import org.web3j.protocol.core.methods.response.EthGetFullBalance;
import org.web3j.protocol.core.methods.response.EthGetStorageAt;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthGetUncleCountByBlockHash;
import org.web3j.protocol.core.methods.response.EthGetUncleCountByBlockNumber;
import org.web3j.protocol.core.methods.response.EthHashrate;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthMining;
import org.web3j.protocol.core.methods.response.EthProtocolVersion;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.EthUninstallFilter;
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.ShhNewGroup;
import org.web3j.protocol.core.methods.response.ShhNewIdentity;
import org.web3j.protocol.core.methods.response.ShhVersion;
import org.web3j.protocol.core.methods.response.TdmGeneratePrivateValidator;
import org.web3j.protocol.core.methods.response.TdmGetCurrentEpochNumber;
import org.web3j.protocol.core.methods.response.TdmGetEpoch;
import org.web3j.protocol.core.methods.response.TdmGetNextEpochValidators;
import org.web3j.protocol.core.methods.response.TdmGetNextEpochVote;
import org.web3j.protocol.core.methods.response.TdmRevealVote;
import org.web3j.protocol.core.methods.response.TdmVoteNextEpoch;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.Web3Sha3;
import org.web3j.protocol.http.HttpService;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JSON-RPC 2.0 Integration Tests.
 */
public class CoreIT {

    private Web3j web3j;

    private IntegrationTestConfig config = new TestnetConfig();

    public CoreIT() { }

    @Before
    public void setUp() {
        this.web3j = Web3j.build(new HttpService());
    }

    @Test
    public void testWeb3ClientVersion() throws Exception {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println("Pchain client version: " + clientVersion);
        assertFalse(clientVersion.isEmpty());
    }

    @Test
    public void testWeb3Sha3() throws Exception {
        Web3Sha3 web3Sha3 = web3j.web3Sha3("0x68656c6c6f20776f726c64").send();
        assertThat(web3Sha3.getResult(),
                is("0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fad"));
    }

    @Test
    public void testNetVersion() throws Exception {
        NetVersion netVersion = web3j.netVersion().send();
        assertFalse(netVersion.getNetVersion().isEmpty());
    }

    @Test
    public void testNetListening() throws Exception {
        NetListening netListening = web3j.netListening().send();
        assertTrue(netListening.isListening());
    }

    @Test
    public void testNetPeerCount() throws Exception {
        NetPeerCount netPeerCount = web3j.netPeerCount().send();
        assertTrue(netPeerCount.getQuantity().signum() == 1);
    }

    @Test
    public void testEthProtocolVersion() throws Exception {
        EthProtocolVersion ethProtocolVersion = web3j.ethProtocolVersion().send();
        assertFalse(ethProtocolVersion.getProtocolVersion().isEmpty());
    }

    @Test
    public void testEthSyncing() throws Exception {
        EthSyncing ethSyncing = web3j.ethSyncing().send();
        assertNotNull(ethSyncing.getResult());
    }

    @Test
    public void testEthCoinbase() throws Exception {
        EthCoinbase ethCoinbase = web3j.ethCoinbase().send();
        assertNotNull(ethCoinbase.getAddress());
    }

    @Test
    public void testEthMining() throws Exception {
        EthMining ethMining = web3j.ethMining().send();
        assertNotNull(ethMining.getResult());
    }

    @Test
    public void testEthHashrate() throws Exception {
        EthHashrate ethHashrate = web3j.ethHashrate().send();
        assertThat(ethHashrate.getHashrate(), is(BigInteger.ZERO));
    }

    @Test
    public void testEthGasPrice() throws Exception {
        EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
        assertTrue(ethGasPrice.getGasPrice().signum() == 1);
    }

    @Test
    public void testEthAccounts() throws Exception {
        EthAccounts ethAccounts = web3j.ethAccounts().send();
        assertNotNull(ethAccounts.getAccounts());
    }

    @Test
    public void testEthBlockNumber() throws Exception {
        EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
        assertTrue(ethBlockNumber.getBlockNumber().signum() == 1);
    }

    @Test
    public void testEthGetBalance() throws Exception {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(
                config.validAccount(), DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(ethGetBalance.getBalance().signum() == 1);
    }

    @Test
    public void testEthGetStorageAt() throws Exception {
        EthGetStorageAt ethGetStorageAt = web3j.ethGetStorageAt(
                config.validContractAddress(),
                BigInteger.valueOf(0),
                DefaultBlockParameter.valueOf("latest")).send();
        assertThat(ethGetStorageAt.getData(), is(config.validContractAddressPositionZero()));
    }

    @Test
    public void testEthGetTransactionCount() throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                config.validAccount(),
                DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(ethGetTransactionCount.getTransactionCount().signum() == 1);
    }

    @Test
    public void testEthGetBlockTransactionCountByHash() throws Exception {
        EthGetBlockTransactionCountByHash ethGetBlockTransactionCountByHash =
                web3j.ethGetBlockTransactionCountByHash(
                        config.validBlockHash()).send();
        assertThat(ethGetBlockTransactionCountByHash.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    @Test
    public void testEthGetBlockTransactionCountByNumber() throws Exception {
        EthGetBlockTransactionCountByNumber ethGetBlockTransactionCountByNumber =
                web3j.ethGetBlockTransactionCountByNumber(
                        DefaultBlockParameter.valueOf(config.validBlock())).send();
        assertThat(ethGetBlockTransactionCountByNumber.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    @Test
    public void testEthGetUncleCountByBlockHash() throws Exception {
        EthGetUncleCountByBlockHash ethGetUncleCountByBlockHash =
                web3j.ethGetUncleCountByBlockHash(config.validBlockHash()).send();
        assertThat(ethGetUncleCountByBlockHash.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    @Test
    public void testEthGetUncleCountByBlockNumber() throws Exception {
        EthGetUncleCountByBlockNumber ethGetUncleCountByBlockNumber =
                web3j.ethGetUncleCountByBlockNumber(
                        DefaultBlockParameter.valueOf("latest")).send();
        assertThat(ethGetUncleCountByBlockNumber.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    @Test
    public void testEthGetCode() throws Exception {
        EthGetCode ethGetCode = web3j.ethGetCode(config.validContractAddress(),
                DefaultBlockParameter.valueOf(config.validBlock())).send();
        assertThat(ethGetCode.getCode(), is(config.validContractCode()));
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testEthSign() throws Exception {
        // EthSign ethSign = web3j.ethSign();
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testEthSendTransaction() throws Exception {
        EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(
                config.buildTransaction()).send();
        assertFalse(ethSendTransaction.getTransactionHash().isEmpty());
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testEthSendRawTransaction() throws Exception {

    }

    @Test
    public void testEthCall() throws Exception {
        EthCall ethCall = web3j.ethCall(config.buildTransaction(),
                DefaultBlockParameter.valueOf("latest")).send();

        assertThat(DefaultBlockParameterName.LATEST.getValue(), is("latest"));
        assertThat(ethCall.getValue(), is("0x"));
    }

    @Test
    public void testEthEstimateGas() throws Exception {
        EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(config.buildTransaction())
                .send();
        assertTrue(ethEstimateGas.getAmountUsed().signum() == 1);
    }

    @Test
    public void testEthGetBlockByHashReturnHashObjects() throws Exception {
        EthBlock ethBlock = web3j.ethGetBlockByHash(config.validBlockHash(), false)
                .send();

        EthBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                is(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEthGetBlockByHashReturnFullTransactionObjects() throws Exception {
        EthBlock ethBlock = web3j.ethGetBlockByHash(config.validBlockHash(), true)
                .send();

        EthBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEthGetBlockByNumberReturnHashObjects() throws Exception {
        EthBlock ethBlock = web3j.ethGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), false).send();

        EthBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEthGetBlockByNumberReturnTransactionObjects() throws Exception {
        EthBlock ethBlock = web3j.ethGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), true).send();

        EthBlock.Block block = ethBlock.getBlock();
        assertNotNull(ethBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEthGetTransactionByHash() throws Exception {
        EthTransaction ethTransaction = web3j.ethGetTransactionByHash(
                config.validTransactionHash()).send();
        assertTrue(ethTransaction.getTransaction().isPresent());
        Transaction transaction = ethTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
    }

    @Test
    public void testEthGetTransactionByBlockHashAndIndex() throws Exception {
        BigInteger index = BigInteger.ONE;

        EthTransaction ethTransaction = web3j.ethGetTransactionByBlockHashAndIndex(
                config.validBlockHash(), index).send();
        assertTrue(ethTransaction.getTransaction().isPresent());
        Transaction transaction = ethTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    @Test
    public void testEthGetTransactionByBlockNumberAndIndex() throws Exception {
        BigInteger index = BigInteger.ONE;

        EthTransaction ethTransaction = web3j.ethGetTransactionByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validBlock()), index).send();
        assertTrue(ethTransaction.getTransaction().isPresent());
        Transaction transaction = ethTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    @Test
    public void testEthGetTransactionReceipt() throws Exception {
        EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(
                config.validTransactionHash()).send();
        assertTrue(ethGetTransactionReceipt.getTransactionReceipt().isPresent());
        TransactionReceipt transactionReceipt =
                ethGetTransactionReceipt.getTransactionReceipt().get();
        assertThat(transactionReceipt.getTransactionHash(), is(config.validTransactionHash()));
    }

    @Test
    public void testEthGetUncleByBlockHashAndIndex() throws Exception {
        EthBlock ethBlock = web3j.ethGetUncleByBlockHashAndIndex(
                config.validUncleBlockHash(), BigInteger.ZERO).send();
        assertNotNull(ethBlock.getBlock());
    }

    @Test
    public void testEthGetUncleByBlockNumberAndIndex() throws Exception {
        EthBlock ethBlock = web3j.ethGetUncleByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validUncleBlock()), BigInteger.ZERO)
                .send();
        assertNotNull(ethBlock.getBlock());
    }

    @Test
    public void testEthGetCompilers() throws Exception {
        EthGetCompilers ethGetCompilers = web3j.ethGetCompilers().send();
        assertNotNull(ethGetCompilers.getCompilers());
    }

    @Ignore  // The method eth_compileLLL does not exist/is not available
    @Test
    public void testEthCompileLLL() throws Exception {
        EthCompileLLL ethCompileLLL = web3j.ethCompileLLL(
                "(returnlll (suicide (caller)))").send();
        assertFalse(ethCompileLLL.getCompiledSourceCode().isEmpty());
    }

    @Test
    public void testEthCompileSolidity() throws Exception {
        String sourceCode = "pragma solidity ^0.4.0;"
                + "\ncontract test { function multiply(uint a) returns(uint d) {"
                + "   return a * 7;   } }"
                + "\ncontract test2 { function multiply2(uint a) returns(uint d) {"
                + "   return a * 7;   } }";
        EthCompileSolidity ethCompileSolidity = web3j.ethCompileSolidity(sourceCode)
                .send();
        assertNotNull(ethCompileSolidity.getCompiledSolidity());
        assertThat(
                ethCompileSolidity.getCompiledSolidity().get("test2").getInfo().getSource(),
                is(sourceCode));
    }

    @Ignore  // The method eth_compileSerpent does not exist/is not available
    @Test
    public void testEthCompileSerpent() throws Exception {
        EthCompileSerpent ethCompileSerpent = web3j.ethCompileSerpent(
                "/* some serpent */").send();
        assertFalse(ethCompileSerpent.getCompiledSourceCode().isEmpty());
    }

    @Test
    public void testFiltersByFilterId() throws Exception {
        org.web3j.protocol.core.methods.request.EthFilter ethFilter =
                new org.web3j.protocol.core.methods.request.EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                config.validContractAddress());

        String eventSignature = config.encodedEvent();
        ethFilter.addSingleTopic(eventSignature);

        // eth_newFilter
        EthFilter ethNewFilter = web3j.ethNewFilter(ethFilter).send();
        BigInteger filterId = ethNewFilter.getFilterId();

        // eth_getFilterLogs
        EthLog ethFilterLogs = web3j.ethGetFilterLogs(filterId).send();
        List<EthLog.LogResult> filterLogs = ethFilterLogs.getLogs();
        assertFalse(filterLogs.isEmpty());

        // eth_getFilterChanges - nothing will have changed in this interval
        EthLog ethLog = web3j.ethGetFilterChanges(filterId).send();
        assertTrue(ethLog.getLogs().isEmpty());

        // eth_uninstallFilter
        EthUninstallFilter ethUninstallFilter = web3j.ethUninstallFilter(filterId).send();
        assertTrue(ethUninstallFilter.isUninstalled());
    }

    @Test
    public void testEthNewBlockFilter() throws Exception {
        EthFilter ethNewBlockFilter = web3j.ethNewBlockFilter().send();
        assertNotNull(ethNewBlockFilter.getFilterId());
    }

    @Test
    public void testEthNewPendingTransactionFilter() throws Exception {
        EthFilter ethNewPendingTransactionFilter =
                web3j.ethNewPendingTransactionFilter().send();
        assertNotNull(ethNewPendingTransactionFilter.getFilterId());
    }

    @Test
    public void testEthGetLogs() throws Exception {
        org.web3j.protocol.core.methods.request.EthFilter ethFilter =
                new org.web3j.protocol.core.methods.request.EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                config.validContractAddress()
        );

        ethFilter.addSingleTopic(config.encodedEvent());

        EthLog ethLog = web3j.ethGetLogs(ethFilter).send();
        List<EthLog.LogResult> logs = ethLog.getLogs();
        assertFalse(logs.isEmpty());
    }

    // @Test
    // public void testEthGetWork() throws Exception {
    //     EthGetWork ethGetWork = requestFactory.ethGetWork();
    //     assertNotNull(ethGetWork.getResult());
    // }

    @Test
    public void testEthSubmitWork() throws Exception {

    }

    @Test
    public void testEthSubmitHashrate() throws Exception {
    
    }

    @Test
    public void testDbPutString() throws Exception {
    
    }

    @Test
    public void testDbGetString() throws Exception {
    
    }

    @Test
    public void testDbPutHex() throws Exception {
    
    }

    @Test
    public void testDbGetHex() throws Exception {
    
    }

    @Test
    public void testShhPost() throws Exception {
    
    }

    @Ignore // The method shh_version does not exist/is not available
    @Test
    public void testShhVersion() throws Exception {
        ShhVersion shhVersion = web3j.shhVersion().send();
        assertNotNull(shhVersion.getVersion());
    }

    @Ignore  // The method shh_newIdentity does not exist/is not available
    @Test
    public void testShhNewIdentity() throws Exception {
        ShhNewIdentity shhNewIdentity = web3j.shhNewIdentity().send();
        assertNotNull(shhNewIdentity.getAddress());
    }

    @Test
    public void testShhHasIdentity() throws Exception {
    
    }

    @Ignore  // The method shh_newIdentity does not exist/is not available
    @Test
    public void testShhNewGroup() throws Exception {
        ShhNewGroup shhNewGroup = web3j.shhNewGroup().send();
        assertNotNull(shhNewGroup.getAddress());
    }

    @Ignore  // The method shh_addToGroup does not exist/is not available
    @Test
    public void testShhAddToGroup() throws Exception {

    }

    @Test
    public void testShhNewFilter() throws Exception {
    
    }

    @Test
    public void testShhUninstallFilter() throws Exception {
    
    }

    @Test
    public void testShhGetFilterChanges() throws Exception {
    
    }

    @Test
    public void testShhGetMessages() throws Exception {
    
    }
    
    @Test
    public void testChainCreateChildChain() throws Exception {
    	ChainCreateChildChain chainCreateChildChain = web3j.chainCreateChildChain("0xa349d8a4e35f0c922377168daae653b5c9f1d370",
    	    	"pchain-child-8", "0x1", "0x152D02C7E14AF6800000", "0x32","0x7D0", null).send();
    	
    	assertFalse(chainCreateChildChain.getHash().isEmpty());
    }
    
    @Test
    public void testChainJoinChildChain() throws Exception {
    	ChainJoinChildChain chainJoinChildChain = web3j.chainJoinChildChain("0x5CE010Bf008Ba976Dd80Ed968a2f916190Cf9b4f",
    			  "5CCB45F727A7075C9336DF357A3610DD884FD97E64FFB51EED30890B8B3519E36D1C211A7BC1335C09CE654779328F1D01D997C1B2C5F9D196AD67FA5AF7A00273CED363C50D8F12B4EA096AFB859D6311C63C910752D41C0532C2D2654DCA863F7D56B2B9C33E0E7A5A0349F6B4FC20AE15526C5463F11D76FA92AB183ECEBE",
    			   "pchain-child-8",
    			   "0x152D02C7E14AF6800000",
    			   "0x6e5ea219800849592e67f76d45742a29c42a20b0b9d853facf32ac788591869e3db50a10770d88b93f24d2f6efed8acd220bce6442db7a2fbadfdada2d2cde73",
    			   null).send();
    	
    	assertFalse(chainJoinChildChain.getHash().isEmpty());
    }
    
    @Test
    public void testChainDepositInMainChain() throws Exception {
    	ChainDepositInMainChain chainDepositInMainChain = web3j.chainDepositInMainChain(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534", 
    			"pchain_child_0", 
    			"0xA968163F0A57B4000000", 
    			"0x5208").send();
    	
    	assertFalse(chainDepositInMainChain.getHash().isEmpty());
    }
    
    @Test
    public void testChainDepositInChildChain() throws Exception {
    	ChainDepositInChildChain chainDepositInChildChain = web3j.chainDepositInChildChain(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			"0x31d6fe38869272a821ac7a2b3b00aba9cb486f02cc570895f8f5d2dea8f7b5dc").send();
    	
    	assertFalse(chainDepositInChildChain.getHash().isEmpty());
    }
    
    @Test
    public void testChainWithdrawFromChildChain() throws Exception {
    	ChainWithdrawFromChildChain chainWithdrawFromChildChain = web3j.chainWithdrawFromChildChain(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
 			   "0x3F870857A3E0E3800000", null).send();
    	
    	assertFalse(chainWithdrawFromChildChain.getHash().isEmpty());
    }
    

    @Test
    public void testChainWithdrawFromMainChain() throws Exception {
    	ChainWithdrawFromMainChain chainWithdrawFromMainChain = web3j.chainWithdrawFromMainChain(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			"0x3F870857A3E0E3800000",
    			"pchain_child_0",
    			"0x6ff2ac4bb53ef7907bef3219eb3f2684b66df8a22048a80270960f9671ed0007").send();
    	
    	assertFalse(chainWithdrawFromMainChain.getHash().isEmpty());
    }
    
    @Test
    public void testChainSignAddress() throws Exception {
    	ChainSignAddress chainSignAddress = web3j.chainSignAddress(
    			"0x54A1E8C1F8941B4D88865BE69D4180C6466271B4",
    			"0x14BCEEA31D3ABC5DF66410EDDBA6F66B13CD90D7FA079B352263821F81BB8C81").send();
    	
    	assertTrue(chainSignAddress.getDATA() == "0x8189a1f9432649ef8708e76e00448168e177667ba16a68ae4650b1b3eab0ea4d154c58ccb4f422422a7db37911d90164271d4e8dd18683dde60286bed4adede1");
    }

    @Test
    public void testChainSetBlockReward() throws Exception {
    	ChainSetBlockReward chainSetBlockReward = web3j.chainSetBlockReward(
    			"0xFD6AA07FF92907886B10B8E8863DDF8BA1902109","0x10").send();
    	
    	assertFalse(chainSetBlockReward.getHash().isEmpty());
    }

    @Test
    public void testChainGetBlockReward() throws Exception {
    	ChainGetBlockReward chainGetBlockReward = web3j.chainGetBlockReward(
    			new DefaultBlockParameterNumber(6)).send();
    	
    	assertTrue(chainGetBlockReward.getReward().intValue() >= 0);
    }

    @Test
    public void testChainGetAllChains() throws Exception {
    	ChainGetAllChains chainGetAllChains = web3j.chainGetAllChains().send();
    	
    	assertTrue(chainGetAllChains.getChains().size() >= 0);
    }
    
    @Test
    public void testTdmGetCurrentEpochNumber() throws Exception {
    	TdmGetCurrentEpochNumber tdmGetCurrentEpochNumber = web3j.tdmGetCurrentEpochNumber().send();
        assertTrue(tdmGetCurrentEpochNumber.getCurrentEpochNumber() >= 0);
    }
    
    @Test
    public void testTdmGetEpoch() throws Exception {
    	TdmGetEpoch tdmGetEpoch = web3j.tdmGetEpoch(0).send();
        assertTrue(tdmGetEpoch.getEpoch().getNumber() == 0);
    }
    
    @Test
    public void testTdmVoteNextEpoch() throws Exception {
    	TdmVoteNextEpoch tdmVoteNextEpoch = web3j.tdmVoteNextEpoch(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			"0xa394508546db4256283598119da9638fdb62655572722c6e2abea5f150512f2d",
    			null).send();
    	
    	assertFalse(tdmVoteNextEpoch.getHash().isEmpty());
    }

    @Test
    public void testTdmRevealVote() throws Exception {
    	TdmRevealVote tdmRevealVote = web3j.tdmRevealVote(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			"04A77BB50F7D3993CC6485CAABF8FE1980EDAAE88635A1FCB6EFE577D4C10166F0BA4D9C1AC53461FE3332292DDC8594C92E0E4D2C0CEEE0F74D8D67ACD8E391B1",
    			"0x152D02C7E14AF6800000",
    			"tlas",
    			"0x6e5ea219800849592e67f76d45742a29c42a20b0b9d853facf32ac788591869e3db50a10770d88b93f24d2f6efed8acd220bce6442db7a2fbadfdada2d2cde73",
    			null).send();
    	
    	assertFalse(tdmRevealVote.getHash().isEmpty());
    }
    
    @Test
    public void testTdmGetNextEpochVote() throws Exception {
    	TdmGetNextEpochVote tdmGetNextEpochVote = web3j.tdmGetNextEpochVote().send();
    	
    	assertTrue(tdmGetNextEpochVote.getVote() != null);
    }
    
    @Test
    public void testTdmGetNextEpochValidators() throws Exception {
    	TdmGetNextEpochValidators tdmGetNextEpochValidators = web3j.tdmGetNextEpochValidators().send();
    	
    	assertTrue(tdmGetNextEpochValidators.getEpochValidators() != null);
    }
    
    @Test
    public void testTdmGeneratePrivateValidator() throws Exception {
    	TdmGeneratePrivateValidator tdmGeneratePrivateValidator = 
    			web3j.tdmGeneratePrivateValidator("0x1234567890123456789012345678901234567890").send();
    	
    	assertTrue(tdmGeneratePrivateValidator.getPrivateValidator().getConsensus_pub_key() != null);
    }
    
    @Test
    public void testDelDelegate() throws Exception {
    	DelDelegate delDelegate = web3j.delDelegate("0x1529FA43D9F7FE958662F7200739CDC3EC2666C7","0xd833b6738285f4a50cf42cf1a40c4000256589d4", "0x3635c9adc5dea00000", null).send();
    	
    	assertFalse(delDelegate.getHash().isEmpty());
    }
    
    @Test
    public void testDelCancelDelegate() throws Exception {
    	DelCancelDelegate delCancelDelegate = web3j.delCancelDelegate("0x1529FA43D9F7FE958662F7200739CDC3EC2666C7","0xd833b6738285f4a50cf42cf1a40c4000256589d4", "0x3635c9adc5dea00000", null).send();
    	
    	assertFalse(delCancelDelegate.getHash().isEmpty());
    }
    
    @Test
    public void testDelApplyCandidate() throws Exception {
    	DelApplyCandidate delApplyCandidate = web3j.delApplyCandidate("0xd833b6738285f4a50cf42cf1a40c4000256589d4","0x21e19e0c9bab2400000", 10, null).send();
    	
    	assertFalse(delApplyCandidate.getHash().isEmpty());
    }

    @Test
    public void testDelCancelCandidate() throws Exception {
    	DelCancelCandidate delCancelCandidate = web3j.delCancelCandidate("0xd833b6738285f4a50cf42cf1a40c4000256589d4", null).send();
    	
    	assertFalse(delCancelCandidate.getHash().isEmpty());
    }

    @Test
    public void testDelCheckCandidate() throws Exception {
    	DelCheckCandidate delCheckCandidate = web3j.delCheckCandidate("0xd833b6738285f4a50cf42cf1a40c4000256589d4", new DefaultBlockParameterNumber(10)).send();
    	
    	assertTrue(delCheckCandidate.getCandidateState().getCommission() >= 0);
    }

    @Test
    public void testEthGetFullBalance() throws Exception {
    	EthGetFullBalance ethGetFullBalance = web3j.ethGetFullBalance("0xC6179A651918888251380A4E3FEE6AF81CF091D1",DefaultBlockParameterName.LATEST, true).send();
    	
    	assertTrue(ethGetFullBalance.getFullBalance().getBalance().compareTo(BigInteger.ZERO) >= 0);
    }

}
