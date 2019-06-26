package org.pweb3j.protocol.scenarios;

import java.math.BigInteger;

import org.junit.Test;

import org.pweb3j.abi.datatypes.generated.Uint256;
import org.pweb3j.generated.Fibonacci;
import org.pweb3j.protocol.Web3j;
import org.pweb3j.protocol.core.methods.response.TransactionReceipt;
import org.pweb3j.protocol.http.HttpService;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test Fibonacci contract generated wrappers.
 *
 * <p>Generated via running org.pweb3j.codegen.SolidityFunctionWrapperGenerator with params:
 * <em>project-home</em>/src/test/resources/solidity/fibonacci.abi -o
 * <em>project-home</em>/src/integration-test/java -p org.pweb3j.generated
 */
public class FunctionWrappersIT extends Scenario {

    @Test
    public void testFibonacci() throws Exception {
        Fibonacci fibonacci = Fibonacci.load(
                "0x3c05b2564139fb55820b18b72e94b2178eaace7d", Web3j.build(new HttpService()),
                ALICE, STATIC_GAS_PROVIDER);

        BigInteger result = fibonacci.fibonacci(BigInteger.valueOf(10)).send();
        assertThat(result, equalTo(BigInteger.valueOf(55)));
    }

    @Test
    public void testFibonacciNotify() throws Exception {
        Fibonacci fibonacci = Fibonacci.load(
                "0x3c05b2564139fb55820b18b72e94b2178eaace7d", Web3j.build(new HttpService()),
                ALICE, STATIC_GAS_PROVIDER);

        TransactionReceipt transactionReceipt = fibonacci.fibonacciNotify(
                BigInteger.valueOf(15)).send();

        Fibonacci.NotifyEventResponse result = fibonacci.getNotifyEvents(transactionReceipt).get(0);

        assertThat(result.input,
                equalTo(new Uint256(BigInteger.valueOf(15))));

        assertThat(result.result,
                equalTo(new Uint256(BigInteger.valueOf(610))));
    }
}
