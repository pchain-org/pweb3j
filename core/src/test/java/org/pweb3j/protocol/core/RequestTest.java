package org.pweb3j.protocol.core;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import org.pweb3j.protocol.RequestTester;
import org.pweb3j.protocol.Web3j;
import org.pweb3j.protocol.core.methods.request.EthFilter;
import org.pweb3j.protocol.core.methods.request.ShhFilter;
import org.pweb3j.protocol.core.methods.request.ShhPost;
import org.pweb3j.protocol.core.methods.request.Transaction;
import org.pweb3j.protocol.http.HttpService;
import org.pweb3j.utils.Numeric;

public class RequestTest extends RequestTester {

    private Web3j web3j;

    @Override
    protected void initWeb3Client(HttpService httpService) {
        web3j = Web3j.build(httpService);
    }

    @Test
    public void testWeb3ClientVersion() throws Exception {
        web3j.web3ClientVersion().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"web3_clientVersion\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testWeb3Sha3() throws Exception {
        web3j.web3Sha3("0x68656c6c6f20776f726c64").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"web3_sha3\","
                        + "\"params\":[\"0x68656c6c6f20776f726c64\"],\"id\":1}");
    }

    @Test
    public void testNetVersion() throws Exception {
        web3j.netVersion().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"net_version\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testNetListening() throws Exception {
        web3j.netListening().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"net_listening\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testNetPeerCount() throws Exception {
        web3j.netPeerCount().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"net_peerCount\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthProtocolVersion() throws Exception {
        web3j.ethProtocolVersion().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_protocolVersion\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthSyncing() throws Exception {
        web3j.ethSyncing().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_syncing\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthCoinbase() throws Exception {
        web3j.ethCoinbase().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_coinbase\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthMining() throws Exception {
        web3j.ethMining().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_mining\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthHashrate() throws Exception {
        web3j.ethHashrate().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_hashrate\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthGasPrice() throws Exception {
        web3j.ethGasPrice().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_gasPrice\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthAccounts() throws Exception {
        web3j.ethAccounts().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_accounts\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthBlockNumber() throws Exception {
        web3j.ethBlockNumber().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_blockNumber\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthGetBalance() throws Exception {
        web3j.ethGetBalance("0x407d73d8a49eeb85d32cf465507dd71d507100c1",
                DefaultBlockParameterName.LATEST).send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getBalance\","
                        + "\"params\":[\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\"latest\"],"
                        + "\"id\":1}");
    }

    @Test
    public void testEthGetStorageAt() throws Exception {
        web3j.ethGetStorageAt("0x295a70b2de5e3953354a6a8344e616ed314d7251", BigInteger.ZERO,
                DefaultBlockParameterName.LATEST).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getStorageAt\","
                + "\"params\":[\"0x295a70b2de5e3953354a6a8344e616ed314d7251\",\"0x0\",\"latest\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEthGetTransactionCount() throws Exception {
        web3j.ethGetTransactionCount("0x407d73d8a49eeb85d32cf465507dd71d507100c1",
                DefaultBlockParameterName.LATEST).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionCount\","
                + "\"params\":[\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\"latest\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEthGetBlockTransactionCountByHash() throws Exception {
        web3j.ethGetBlockTransactionCountByHash(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getBlockTransactionCountByHash\",\"params\":[\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEthGetBlockTransactionCountByNumber() throws Exception {
        web3j.ethGetBlockTransactionCountByNumber(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0xe8"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getBlockTransactionCountByNumber\","
                + "\"params\":[\"0xe8\"],\"id\":1}");
    }

    @Test
    public void testEthGetUncleCountByBlockHash() throws Exception {
        web3j.ethGetUncleCountByBlockHash(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getUncleCountByBlockHash\",\"params\":[\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEthGetUncleCountByBlockNumber() throws Exception {
        web3j.ethGetUncleCountByBlockNumber(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0xe8"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getUncleCountByBlockNumber\","
                + "\"params\":[\"0xe8\"],\"id\":1}");
    }

    @Test
    public void testEthGetCode() throws Exception {
        web3j.ethGetCode("0xa94f5374fce5edbc8e2a8697c15331677e6ebf0b",
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x2"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getCode\","
                + "\"params\":[\"0xa94f5374fce5edbc8e2a8697c15331677e6ebf0b\",\"0x2\"],\"id\":1}");
    }

    @Test
    public void testEthSign() throws Exception {
        web3j.ethSign("0x8a3106a3e50576d4b6794a0e74d3bb5f8c9acaab",
                "0xc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sign\","
                + "\"params\":[\"0x8a3106a3e50576d4b6794a0e74d3bb5f8c9acaab\","
                + "\"0xc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEthSendTransaction() throws Exception {
        web3j.ethSendTransaction(new Transaction(
                "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
                BigInteger.ONE,
                Numeric.toBigInt("0x9184e72a000"),
                Numeric.toBigInt("0x76c0"),
                "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
                Numeric.toBigInt("0x9184e72a"),
                "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb"
                        + "970870f072445675058bb8eb970870f072445675")).send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendTransaction\",\"params\":[{\"from\":\"0xb60e8dd61c5d32be8058bb8eb970870f07233155\",\"to\":\"0xb60e8dd61c5d32be8058bb8eb970870f07233155\",\"gas\":\"0x76c0\",\"gasPrice\":\"0x9184e72a000\",\"value\":\"0x9184e72a\",\"data\":\"0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675\",\"nonce\":\"0x1\"}],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEthSendRawTransaction() throws Exception {
        web3j.ethSendRawTransaction(
                "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f"
                        + "072445675058bb8eb970870f072445675").send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendRawTransaction\",\"params\":[\"0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675\"],\"id\":1}");
        //CHECKSTYLE:ON
    }


    @Test
    public void testEthCall() throws Exception {
        web3j.ethCall(Transaction.createEthCallTransaction(
                "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
                "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
                        "0x0"),
                DefaultBlockParameter.valueOf("latest")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_call\","
                + "\"params\":[{\"from\":\"0xa70e8dd61c5d32be8058bb8eb970870f07233155\","
                + "\"to\":\"0xb60e8dd61c5d32be8058bb8eb970870f07233155\",\"data\":\"0x0\"},"
                + "\"latest\"],\"id\":1}");
    }

    @Test
    public void testEthEstimateGas() throws Exception {
        web3j.ethEstimateGas(
                Transaction.createEthCallTransaction(
                        "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
                        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f", "0x0")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_estimateGas\","
                + "\"params\":[{\"from\":\"0xa70e8dd61c5d32be8058bb8eb970870f07233155\","
                + "\"to\":\"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f\",\"data\":\"0x0\"}],"
                + "\"id\":1}");
    }

    @Test
    public void testEthEstimateGasContractCreation() throws Exception {
        web3j.ethEstimateGas(
                Transaction.createContractTransaction(
                        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f", BigInteger.ONE,
                        BigInteger.TEN, "")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_estimateGas\","
                + "\"params\":[{\"from\":\"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f\","
                + "\"gasPrice\":\"0xa\",\"data\":\"0x\",\"nonce\":\"0x1\"}],\"id\":1}");
    }

    @Test
    public void testEthGetBlockByHash() throws Exception {
        web3j.ethGetBlockByHash(
                "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331", true).send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getBlockByHash\",\"params\":["
                        + "\"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\""
                        + ",true],\"id\":1}");
    }

    @Test
    public void testEthGetBlockByNumber() throws Exception {
        web3j.ethGetBlockByNumber(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x1b4")), true).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getBlockByNumber\","
                + "\"params\":[\"0x1b4\",true],\"id\":1}");
    }

    @Test
    public void testEthGetTransactionByHash() throws Exception {
        web3j.ethGetTransactionByHash(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionByHash\",\"params\":["
                + "\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEthGetTransactionByBlockHashAndIndex() throws Exception {
        web3j.ethGetTransactionByBlockHashAndIndex(
                "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
                BigInteger.ZERO).send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionByBlockHashAndIndex\",\"params\":[\"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\",\"0x0\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEthGetTransactionByBlockNumberAndIndex() throws Exception {
        web3j.ethGetTransactionByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x29c")), BigInteger.ZERO).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionByBlockNumberAndIndex\","
                + "\"params\":[\"0x29c\",\"0x0\"],\"id\":1}");
    }

    @Test
    public void testEthGetTransactionReceipt() throws Exception {
        web3j.ethGetTransactionReceipt(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getTransactionReceipt\",\"params\":["
                + "\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEthGetUncleByBlockHashAndIndex() throws Exception {
        web3j.ethGetUncleByBlockHashAndIndex(
                "0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
                BigInteger.ZERO).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getUncleByBlockHashAndIndex\","
                + "\"params\":["
                + "\"0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b\",\"0x0\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEthGetUncleByBlockNumberAndIndex() throws Exception {
        web3j.ethGetUncleByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x29c")), BigInteger.ZERO).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getUncleByBlockNumberAndIndex\","
                + "\"params\":[\"0x29c\",\"0x0\"],\"id\":1}");
    }

    @Test
    public void testEthGetCompilers() throws Exception {
        web3j.ethGetCompilers().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getCompilers\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthCompileSolidity() throws Exception {
        web3j.ethCompileSolidity(
                "contract test { function multiply(uint a) returns(uint d) {   return a * 7;   } }")
                .send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_compileSolidity\","
                + "\"params\":[\"contract test { function multiply(uint a) returns(uint d) {"
                + "   return a * 7;   } }\"],\"id\":1}");
    }

    @Test
    public void testEthCompileLLL() throws Exception {
        web3j.ethCompileLLL("(returnlll (suicide (caller)))").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_compileLLL\","
                + "\"params\":[\"(returnlll (suicide (caller)))\"],\"id\":1}");
    }

    @Test
    public void testEthCompileSerpent() throws Exception {
        web3j.ethCompileSerpent("/* some serpent */").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_compileSerpent\","
                + "\"params\":[\"/* some serpent */\"],\"id\":1}");
    }

    @Test
    public void testEthNewFilter() throws Exception {
        EthFilter ethFilter = new EthFilter()
                .addSingleTopic("0x12341234");

        web3j.ethNewFilter(ethFilter).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_newFilter\","
                + "\"params\":[{\"topics\":[\"0x12341234\"]}],\"id\":1}");
    }

    @Test
    public void testEthNewBlockFilter() throws Exception {
        web3j.ethNewBlockFilter().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_newBlockFilter\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthNewPendingTransactionFilter() throws Exception {
        web3j.ethNewPendingTransactionFilter().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_newPendingTransactionFilter\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthUninstallFilter() throws Exception {
        web3j.ethUninstallFilter(Numeric.toBigInt("0xb")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_uninstallFilter\","
                + "\"params\":[\"0x0b\"],\"id\":1}");
    }

    @Test
    public void testEthGetFilterChanges() throws Exception {
        web3j.ethGetFilterChanges(Numeric.toBigInt("0x16")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getFilterChanges\","
                + "\"params\":[\"0x16\"],\"id\":1}");
    }

    @Test
    public void testEthGetFilterLogs() throws Exception {
        web3j.ethGetFilterLogs(Numeric.toBigInt("0x16")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getFilterLogs\","
                + "\"params\":[\"0x16\"],\"id\":1}");
    }

    @Test
    public void testEthGetLogs() throws Exception {
        web3j.ethGetLogs(new EthFilter().addSingleTopic(
                "0x000000000000000000000000a94f5374fce5edbc8e2a8697c15331677e6ebf0b"))
                .send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\","
                + "\"params\":[{\"topics\":["
                + "\"0x000000000000000000000000a94f5374fce5edbc8e2a8697c15331677e6ebf0b\"]}],"
                + "\"id\":1}");
    }

    @Test
    public void testEthGetLogsWithNumericBlockRange() throws Exception {
        web3j.ethGetLogs(new EthFilter(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0xe8")),
                DefaultBlockParameter.valueOf("latest"), ""))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eth_getLogs\","
                        + "\"params\":[{\"topics\":[],\"fromBlock\":\"0xe8\","
                        + "\"toBlock\":\"latest\",\"address\":[\"\"]}],\"id\":1}");
    }

    @Test
    public void testEthGetWork() throws Exception {
        web3j.ethGetWork().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getWork\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEthSubmitWork() throws Exception {
        web3j.ethSubmitWork("0x0000000000000001",
                "0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
                "0xD1FE5700000000000000000000000000D1FE5700000000000000000000000000").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_submitWork\","
                + "\"params\":[\"0x0000000000000001\","
                + "\"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef\","
                + "\"0xD1FE5700000000000000000000000000D1FE5700000000000000000000000000\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEthSubmitHashRate() throws Exception {
        web3j.ethSubmitHashrate(
                "0x0000000000000000000000000000000000000000000000000000000000500000",
                "0x59daa26581d0acd1fce254fb7e85952f4c09d0915afd33d3886cd914bc7d283c").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_submitHashrate\","
                + "\"params\":["
                + "\"0x0000000000000000000000000000000000000000000000000000000000500000\","
                + "\"0x59daa26581d0acd1fce254fb7e85952f4c09d0915afd33d3886cd914bc7d283c\"],"
                + "\"id\":1}");
    }

    @Test
    public void testDbPutString() throws Exception {
        web3j.dbPutString("testDB", "myKey", "myString").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_putString\","
                + "\"params\":[\"testDB\",\"myKey\",\"myString\"],\"id\":1}");
    }

    @Test
    public void testDbGetString() throws Exception {
        web3j.dbGetString("testDB", "myKey").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_getString\","
                + "\"params\":[\"testDB\",\"myKey\"],\"id\":1}");
    }

    @Test
    public void testDbPutHex() throws Exception {
        web3j.dbPutHex("testDB", "myKey", "0x68656c6c6f20776f726c64").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_putHex\","
                + "\"params\":[\"testDB\",\"myKey\",\"0x68656c6c6f20776f726c64\"],\"id\":1}");
    }

    @Test
    public void testDbGetHex() throws Exception {
        web3j.dbGetHex("testDB", "myKey").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_getHex\","
                + "\"params\":[\"testDB\",\"myKey\"],\"id\":1}");
    }

    @Test
    public void testShhVersion() throws Exception {
        web3j.shhVersion().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_version\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testShhPost() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhPost(new ShhPost(
                "0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1",
                "0x3e245533f97284d442460f2998cd41858798ddf04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a0d4d661997d3940272b717b1",
                Arrays.asList("0x776869737065722d636861742d636c69656e74", "0x4d5a695276454c39425154466b61693532"),
                "0x7b2274797065223a226d6",
                Numeric.toBigInt("0x64"),
                Numeric.toBigInt("0x64"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_post\",\"params\":[{\"from\":\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\",\"to\":\"0x3e245533f97284d442460f2998cd41858798ddf04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a0d4d661997d3940272b717b1\",\"topics\":[\"0x776869737065722d636861742d636c69656e74\",\"0x4d5a695276454c39425154466b61693532\"],\"payload\":\"0x7b2274797065223a226d6\",\"priority\":\"0x64\",\"ttl\":\"0x64\"}],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhNewIdentity() throws Exception {
        web3j.shhNewIdentity().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_newIdentity\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testShhHasIdentity() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhHasIdentity("0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_hasIdentity\",\"params\":[\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhNewGroup() throws Exception {
        web3j.shhNewGroup().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_newGroup\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testShhAddToGroup() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhAddToGroup("0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_addToGroup\",\"params\":[\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhNewFilter() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhNewFilter(
                new ShhFilter("0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1")
                        .addSingleTopic("0x12341234bf4b564f")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_newFilter\",\"params\":[{\"topics\":[\"0x12341234bf4b564f\"],\"to\":\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\"}],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhUninstallFilter() throws Exception {
        web3j.shhUninstallFilter(Numeric.toBigInt("0x7")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_uninstallFilter\","
                + "\"params\":[\"0x07\"],\"id\":1}");
    }

    @Test
    public void testShhGetFilterChanges() throws Exception {
        web3j.shhGetFilterChanges(Numeric.toBigInt("0x7")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_getFilterChanges\","
                + "\"params\":[\"0x07\"],\"id\":1}");
    }

    @Test
    public void testShhGetMessages() throws Exception {
        web3j.shhGetMessages(Numeric.toBigInt("0x7")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_getMessages\","
                + "\"params\":[\"0x07\"],\"id\":1}");
    }

    @Test
    public void testChainCreateChildChain() throws Exception {
    	web3j.chainCreateChildChain("0xa349d8a4e35f0c922377168daae653b5c9f1d370",
    	    	"pchain-child-8", "0x1", "0x152D02C7E14AF6800000", "0x32","0x7D0", null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_createChildChain\","
                + "\"params\":[\"0xa349d8a4e35f0c922377168daae653b5c9f1d370\",\"pchain-child-8\","
                + "\"0x1\",\"0x152D02C7E14AF6800000\",\"0x32\",\"0x7D0\"],\"id\":1}");
    }
    
    @Test
    public void testChainJoinChildChain() throws Exception {
    	web3j.chainJoinChildChain("0x5CE010Bf008Ba976Dd80Ed968a2f916190Cf9b4f",
    			  "5CCB45F727A7075C9336DF357A3610DD884FD97E64FFB51EED30890B8B3519E36D1C211A7BC1335C09CE654779328F1D01D997C1B2C5F9D196AD67FA5AF7A00273CED363C50D8F12B4EA096AFB859D6311C63C910752D41C0532C2D2654DCA863F7D56B2B9C33E0E7A5A0349F6B4FC20AE15526C5463F11D76FA92AB183ECEBE",
    			   "pchain-child-8",
    			   "0x152D02C7E14AF6800000",
    			   "0x6e5ea219800849592e67f76d45742a29c42a20b0b9d853facf32ac788591869e3db50a10770d88b93f24d2f6efed8acd220bce6442db7a2fbadfdada2d2cde73",
    			   null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_joinChildChain\","
                + "\"params\":[\"0x5CE010Bf008Ba976Dd80Ed968a2f916190Cf9b4f\"," + 
                "\"5CCB45F727A7075C9336DF357A3610DD884FD97E64FFB51EED30890B8B3519E36D1C211A7BC1335C09CE654779328F1D01D997C1B2C5F9D196AD67FA5AF7A00273CED363C50D8F12B4EA096AFB859D6311C63C910752D41C0532C2D2654DCA863F7D56B2B9C33E0E7A5A0349F6B4FC20AE15526C5463F11D76FA92AB183ECEBE\"," + 
                "\"pchain-child-8\"," + 
                "\"0x152D02C7E14AF6800000\"," + 
                "\"0x6e5ea219800849592e67f76d45742a29c42a20b0b9d853facf32ac788591869e3db50a10770d88b93f24d2f6efed8acd220bce6442db7a2fbadfdada2d2cde73\"],\"id\":1}");
    }

    @Test
    public void testChainDepositInMainChain() throws Exception {
    	web3j.chainDepositInMainChain("0xB3544059698177F14968D29A25AFD0D6D65F4534", 
    			"pchain_child_0", 
    			"0xA968163F0A57B4000000", 
    			"0x5208").send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_depositInMainChain\","
                + "\"params\":[\"0xB3544059698177F14968D29A25AFD0D6D65F4534\",\"pchain_child_0\","
                + "\"0xA968163F0A57B4000000\",\"0x5208\"],\"id\":1}");
    }
    
    @Test
    public void testChainDepositInChildChain() throws Exception {
    	web3j.chainDepositInChildChain("0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			   "0x31d6fe38869272a821ac7a2b3b00aba9cb486f02cc570895f8f5d2dea8f7b5dc").send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_depositInChildChain\","
                + "\"params\":[\"0xB3544059698177F14968D29A25AFD0D6D65F4534\","
                + "\"0x31d6fe38869272a821ac7a2b3b00aba9cb486f02cc570895f8f5d2dea8f7b5dc\"],\"id\":1}");
    }
    
    @Test
    public void testChainWithdrawFromChildChain() throws Exception {
    	web3j.chainWithdrawFromChildChain("0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			   "0x3F870857A3E0E3800000", null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_withdrawFromChildChain\","
                + "\"params\":[\"0xB3544059698177F14968D29A25AFD0D6D65F4534\","
                + "\"0x3F870857A3E0E3800000\"],\"id\":1}");
    }

    @Test
    public void testChainWithdrawFromMainChain() throws Exception {
    	web3j.chainWithdrawFromMainChain(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			"0x3F870857A3E0E3800000",
    			"pchain_child_0",
    			"0x6ff2ac4bb53ef7907bef3219eb3f2684b66df8a22048a80270960f9671ed0007").send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_withdrawFromMainChain\","
                + "\"params\":[\"0xB3544059698177F14968D29A25AFD0D6D65F4534\","
                + "\"0x3F870857A3E0E3800000\","
                + "\"pchain_child_0\"," 
                + "\"0x6ff2ac4bb53ef7907bef3219eb3f2684b66df8a22048a80270960f9671ed0007\"],\"id\":1}");
    }
    
    @Test
    public void testChainSignAddress() throws Exception {
    	web3j.chainSignAddress(
    			"0xFD6AA07FF92907886B10B8E8863DDF8BA1902109",
    			"0xA1BCB0033FC989D34026EED71AE6C57004CF1FBDC520ABF112B13FF7C03B62C6").send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_signAddress\","
                + "\"params\":[\"0xFD6AA07FF92907886B10B8E8863DDF8BA1902109\","
                + "\"0xA1BCB0033FC989D34026EED71AE6C57004CF1FBDC520ABF112B13FF7C03B62C6\"],\"id\":1}");
    }

    @Test
    public void testChainSetBlockReward() throws Exception {
    	web3j.chainSetBlockReward(
    			"0xFD6AA07FF92907886B10B8E8863DDF8BA1902109","0x10").send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_setBlockReward\","
                + "\"params\":[\"0xFD6AA07FF92907886B10B8E8863DDF8BA1902109\",\"0x10\"],\"id\":1}");
    }

    @Test
    public void testChainGetBlockReward() throws Exception {
    	web3j.chainGetBlockReward(new DefaultBlockParameterNumber(6)).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_getBlockReward\","
                + "\"params\":[\"0x6\"],\"id\":1}");
    }

    @Test
    public void testChainGetAllChains() throws Exception {
    	web3j.chainGetAllChains().send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"chain_getAllChains\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testTdmGetCurrentEpochNumber() throws Exception {
    	web3j.tdmGetCurrentEpochNumber().send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"tdm_getCurrentEpochNumber\","
                + "\"params\":[],\"id\":1}");
    }
    
    @Test
    public void testTdmGetEpoch() throws Exception {
    	web3j.tdmGetEpoch(0).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"tdm_getEpoch\","
                + "\"params\":[0],\"id\":1}");
    }
    
    @Test
    public void testTdmVoteNextEpoch() throws Exception {
    	web3j.tdmVoteNextEpoch(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			"0xa394508546db4256283598119da9638fdb62655572722c6e2abea5f150512f2d",
    			null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"tdm_voteNextEpoch\","
                + "\"params\":[\"0xB3544059698177F14968D29A25AFD0D6D65F4534\",\"0xa394508546db4256283598119da9638fdb62655572722c6e2abea5f150512f2d\"],\"id\":1}");
    }

    @Test
    public void testTdmRevealVote() throws Exception {
    	web3j.tdmRevealVote(
    			"0xB3544059698177F14968D29A25AFD0D6D65F4534",
    			"04A77BB50F7D3993CC6485CAABF8FE1980EDAAE88635A1FCB6EFE577D4C10166F0BA4D9C1AC53461FE3332292DDC8594C92E0E4D2C0CEEE0F74D8D67ACD8E391B1",
    			"0x152D02C7E14AF6800000",
    			"tlas",
    			"0x6e5ea219800849592e67f76d45742a29c42a20b0b9d853facf32ac788591869e3db50a10770d88b93f24d2f6efed8acd220bce6442db7a2fbadfdada2d2cde73",
    			null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"tdm_revealVote\","
                + "\"params\":[\"0xB3544059698177F14968D29A25AFD0D6D65F4534\",\"04A77BB50F7D3993CC6485CAABF8FE1980EDAAE88635A1FCB6EFE577D4C10166F0BA4D9C1AC53461FE3332292DDC8594C92E0E4D2C0CEEE0F74D8D67ACD8E391B1\",\"0x152D02C7E14AF6800000\",\"tlas\",\"0x6e5ea219800849592e67f76d45742a29c42a20b0b9d853facf32ac788591869e3db50a10770d88b93f24d2f6efed8acd220bce6442db7a2fbadfdada2d2cde73\"],\"id\":1}");
    }
    
    @Test
    public void testTdmGetNextEpochVote() throws Exception {
    	web3j.tdmGetNextEpochVote().send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"tdm_getNextEpochVote\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testTdmGetNextEpochValidators() throws Exception {
    	web3j.tdmGetNextEpochValidators().send();
    	
    	verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"tdm_getNextEpochValidators\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testTdmGeneratePrivateValidator() throws Exception {
    	web3j.tdmGeneratePrivateValidator("0x1234567890123456789012345678901234567890").send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"tdm_generatePrivateValidator\",\"params\":[\"0x1234567890123456789012345678901234567890\"],\"id\":1}");
    }

    @Test
    public void testDelDelegate() throws Exception {
    	web3j.delDelegate("0x1529FA43D9F7FE958662F7200739CDC3EC2666C7","0xd833b6738285f4a50cf42cf1a40c4000256589d4", "0x3635c9adc5dea00000", null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"del_delegate\",\"params\":[\"0x1529FA43D9F7FE958662F7200739CDC3EC2666C7\",\"0xd833b6738285f4a50cf42cf1a40c4000256589d4\",\"0x3635c9adc5dea00000\"],\"id\":1}");
    }
    
    @Test
    public void testDelCancelDelegate() throws Exception {
    	web3j.delCancelDelegate("0x1529FA43D9F7FE958662F7200739CDC3EC2666C7","0xd833b6738285f4a50cf42cf1a40c4000256589d4", "0x3635c9adc5dea00000", null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"del_cancelDelegate\",\"params\":[\"0x1529FA43D9F7FE958662F7200739CDC3EC2666C7\",\"0xd833b6738285f4a50cf42cf1a40c4000256589d4\",\"0x3635c9adc5dea00000\"],\"id\":1}");
    }
    
    @Test
    public void testDelApplyCandidate() throws Exception {
    	web3j.delApplyCandidate("0xd833b6738285f4a50cf42cf1a40c4000256589d4","0x21e19e0c9bab2400000", 10, null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"del_applyCandidate\",\"params\":[\"0xd833b6738285f4a50cf42cf1a40c4000256589d4\",\"0x21e19e0c9bab2400000\",10],\"id\":1}");
    }

    @Test
    public void testDelCancelCandidate() throws Exception {
    	web3j.delCancelCandidate("0xd833b6738285f4a50cf42cf1a40c4000256589d4",null).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"del_cancelCandidate\",\"params\":[\"0xd833b6738285f4a50cf42cf1a40c4000256589d4\"],\"id\":1}");
    }

    @Test
    public void testDelCheckCandidate() throws Exception {
    	web3j.delCheckCandidate("0xd833b6738285f4a50cf42cf1a40c4000256589d4",DefaultBlockParameterName.LATEST).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"del_checkCandidate\",\"params\":[\"0xd833b6738285f4a50cf42cf1a40c4000256589d4\",\"latest\"],\"id\":1}");
    }

    @Test
    public void testDelExtractReward() throws Exception {
        web3j.delExtractReward("0xd833b6738285f4a50cf42cf1a40c4000256589d4",null).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"del_extractReward\",\"params\":[\"0xd833b6738285f4a50cf42cf1a40c4000256589d4\"],\"id\":1}");
    }

    @Test
    public void testEthGetFullBalance() throws Exception {
    	web3j.ethGetFullBalance("0xd833b6738285f4a50cf42cf1a40c4000256589d4",DefaultBlockParameterName.LATEST, true).send();
    	
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eth_getFullBalance\",\"params\":[\"0xd833b6738285f4a50cf42cf1a40c4000256589d4\",\"latest\",true],\"id\":1}");
    }

}
