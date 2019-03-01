package org.web3j.protocol.core.methods.request;

import java.io.IOException;
import java.math.BigInteger;

import org.web3j.utils.Numeric;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BigIntegerDeserializer extends JsonDeserializer<BigInteger> {

	@Override
	public BigInteger deserialize(
        JsonParser jsonParser,
        DeserializationContext deserializationContext) throws IOException {
		String a = jsonParser.getText();
		return Numeric.decodeQuantity(a);
	}
}
