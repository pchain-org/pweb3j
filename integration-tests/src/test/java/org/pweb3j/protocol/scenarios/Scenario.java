package org.pweb3j.protocol.scenarios;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Optional;

import org.junit.Before;

import org.pweb3j.abi.TypeReference;
import org.pweb3j.abi.datatypes.Function;
import org.pweb3j.abi.datatypes.Uint;
import org.pweb3j.crypto.Credentials;
import org.pweb3j.protocol.admin.Admin;
import org.pweb3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.pweb3j.protocol.core.DefaultBlockParameterName;
import org.pweb3j.protocol.core.methods.response.EthGetTransactionCount;
import org.pweb3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.pweb3j.protocol.core.methods.response.TransactionReceipt;
import org.pweb3j.protocol.http.HttpService;
import org.pweb3j.tx.gas.ContractGasProvider;
import org.pweb3j.tx.gas.StaticGasProvider;

import static junit.framework.TestCase.fail;

/**
 * Common methods & settings used accross scenarios.
 */
public class Scenario {

    static final BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);
    static final StaticGasProvider STATIC_GAS_PROVIDER =
            new StaticGasProvider(GAS_PRICE, GAS_LIMIT);

    // testnet
    private static final String WALLET_PASSWORD = "";

    /*
    If you want to use regular Ethereum wallet addresses, provide a WALLET address variable
    "0x..." // 20 bytes (40 hex characters) & replace instances of ALICE.getAddress() with this
    WALLET address variable you've defined.
    */
    
	//be very careful to use these ALICE and BOB account, 
	//you may lose your money if you use it in real network
    //ADDRESS = 0x54A1E8C1F8941B4D88865BE69D4180C6466271B4
    static final Credentials ALICE = Credentials.create(
    		"d7ab9617efc574cfc9525fd4e619ef60212fb356f613823f8be81658f6b5a9dc"
    );
    static final String ALICE_BLS_PRIVKEY = "14BCEEA31D3ABC5DF66410EDDBA6F66B13CD90D7FA079B352263821F81BB8C81";
    static final String ALICE_BLS_PUBKEY = "753F042FFDDF162BC9A6F78DE52EAD218BC529C6D4839FB2E6CFC64CB057A4742B169BA5BFD6EA5D38F5EC1B9FA9EC8A72F8CB8C38571219CCFD2EEAFAE5EA9E039E5E6D82ED2C0A13CC53A3F65163D5597438CC03F29C42BC1E7783B1853E8B0CBD03C3C96FD5106182AC0264FC9ABD6F901531DDF436991532D60EF83F282C";

    //0xD3969B10CA915121BAEC113E55D703A38D955FBE
    static final Credentials BOB = Credentials.create(
            "83ab62cfe1221819a169d99f15ee046d5023f5db3d25b50701575ae1f2705c9b"
    );
    static final String BOB_BLS_PRIVKEY = "CAE70BDAC4DC39B77AD26B8179EA26D16DD2382778290B93A3ED4E6F9A1C7BC8";
    static final String BOB_BLS_PUBKEY = "321A973C62AA86A16F6FB5D4E62F6FD1636C75613810F8AEE0CB4EBC7A84B71818CD08A68275B8870806CC6982D542A98894491C450CFFF98272B6A0F9F4466942F986991541D0712F0E1CB96795C42C2EB3559FED374A1091440EB6079DA65F074E78CD5DB73AC83303786DAF4CEC36D87A590AF550EF12D5A94DEEE36A7FB4";

    private static final BigInteger ACCOUNT_UNLOCK_DURATION = BigInteger.valueOf(30);

    private static final int SLEEP_DURATION = 15000;
    private static final int ATTEMPTS = 40;

    Admin web3j;

    public Scenario() { }

    @Before
    public void setUp() {
        this.web3j = Admin.build(new HttpService());
    }

    boolean unlockAccount() throws Exception {
        PersonalUnlockAccount personalUnlockAccount =
                web3j.personalUnlockAccount(
                        ALICE.getAddress(), WALLET_PASSWORD, ACCOUNT_UNLOCK_DURATION)
                        .sendAsync().get();
        return personalUnlockAccount.accountUnlocked();
    }

    TransactionReceipt waitForTransactionReceipt(
            String transactionHash) throws Exception {

        Optional<TransactionReceipt> transactionReceiptOptional =
                getTransactionReceipt(transactionHash, SLEEP_DURATION, ATTEMPTS);

        if (!transactionReceiptOptional.isPresent()) {
            fail("Transaction receipt not generated after " + ATTEMPTS + " attempts");
        }

        return transactionReceiptOptional.get();
    }

    private Optional<TransactionReceipt> getTransactionReceipt(
            String transactionHash, int sleepDuration, int attempts) throws Exception {

        Optional<TransactionReceipt> receiptOptional =
                sendTransactionReceiptRequest(transactionHash);
        for (int i = 0; i < attempts; i++) {
            if (!receiptOptional.isPresent()) {
                Thread.sleep(sleepDuration);
                receiptOptional = sendTransactionReceiptRequest(transactionHash);
            } else {
                break;
            }
        }

        return receiptOptional;
    }

    private Optional<TransactionReceipt> sendTransactionReceiptRequest(
            String transactionHash) throws Exception {
        EthGetTransactionReceipt transactionReceipt =
                web3j.ethGetTransactionReceipt(transactionHash).sendAsync().get();

        return transactionReceipt.getTransactionReceipt();
    }

    BigInteger getNonce(String address) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();

        return ethGetTransactionCount.getTransactionCount();
    }

    Function createFibonacciFunction() {
        return new Function(
                "fibonacciNotify",
                Collections.singletonList(new Uint(BigInteger.valueOf(7))),
                Collections.singletonList(new TypeReference<Uint>() {}));
    }

    static String load(String filePath) throws URISyntaxException, IOException {
        URL url = Scenario.class.getClass().getResource(filePath);
        byte[] bytes = Files.readAllBytes(Paths.get(url.toURI()));
        return new String(bytes);
    }

    static String getFibonacciSolidityBinary() throws Exception {
        return load("/solidity/fibonacci/build/Fibonacci.bin");
    }
}
