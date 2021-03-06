package org.pweb3j.abi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.pweb3j.abi.datatypes.Bool;
import org.pweb3j.abi.datatypes.DynamicArray;
import org.pweb3j.abi.datatypes.DynamicBytes;
import org.pweb3j.abi.datatypes.Fixed;
import org.pweb3j.abi.datatypes.Int;
import org.pweb3j.abi.datatypes.StaticArray;
import org.pweb3j.abi.datatypes.Ufixed;
import org.pweb3j.abi.datatypes.Uint;
import org.pweb3j.abi.datatypes.Utf8String;
import org.pweb3j.abi.datatypes.generated.Int64;
import org.pweb3j.abi.datatypes.generated.StaticArray2;
import org.pweb3j.abi.datatypes.generated.Uint256;
import org.pweb3j.abi.datatypes.generated.Uint64;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.pweb3j.abi.Utils.typeMap;

public class UtilsTest {

    @Test
    public void testGetTypeName() throws ClassNotFoundException {
        assertThat(Utils.getTypeName(new TypeReference<Uint>(){}), is("uint256"));
        assertThat(Utils.getTypeName(new TypeReference<Int>(){}), is("int256"));
        assertThat(Utils.getTypeName(new TypeReference<Ufixed>(){}), is("ufixed256"));
        assertThat(Utils.getTypeName(new TypeReference<Fixed>(){}), is("fixed256"));

        assertThat(Utils.getTypeName(new TypeReference<Uint64>(){}), is("uint64"));
        assertThat(Utils.getTypeName(new TypeReference<Int64>(){}), is("int64"));
        assertThat(Utils.getTypeName(new TypeReference<Bool>(){}), is("bool"));
        assertThat(Utils.getTypeName(new TypeReference<Utf8String>(){}), is("string"));
        assertThat(Utils.getTypeName(new TypeReference<DynamicBytes>(){}), is("bytes"));

        assertThat(Utils.getTypeName(
                new TypeReference.StaticArrayTypeReference<StaticArray<Uint>>(5){}),
                is("uint256[5]"));
        assertThat(Utils.getTypeName(
                new TypeReference<DynamicArray<Uint>>(){}),
                is("uint256[]"));
    }

    @Test
    public void testTypeMap() throws Exception {
        final List<BigInteger> input = Arrays.asList(
                BigInteger.ZERO, BigInteger.ONE, BigInteger.TEN);

        assertThat(typeMap(input, Uint256.class),
                equalTo(Arrays.asList(
                        new Uint256(BigInteger.ZERO),
                        new Uint256(BigInteger.ONE),
                        new Uint256(BigInteger.TEN))));
    }

    @Test
    public void testTypeMapNested() {
        List<BigInteger> innerList1 = Arrays.asList(BigInteger.valueOf(1), BigInteger.valueOf(2));
        List<BigInteger> innerList2 = Arrays.asList(BigInteger.valueOf(3), BigInteger.valueOf(4));
        final List<List<BigInteger>> input = Arrays.asList(innerList1, innerList2);

        StaticArray2<Uint256> staticArray1 = new StaticArray2<>(new Uint256(1), new Uint256(2));
        StaticArray2<Uint256> staticArray2 = new StaticArray2<>(new Uint256(3), new Uint256(4));
        List<StaticArray2<Uint256>> expectedList = Arrays.asList(staticArray1, staticArray2);
        assertThat(typeMap(input, StaticArray2.class, Uint256.class),
                equalTo(expectedList));
    }

    @Test
    public void testTypeMapEmpty() {
        assertThat(typeMap(new ArrayList<BigInteger>(), Uint256.class),
                equalTo(new ArrayList<Uint256>()));
    }
}
