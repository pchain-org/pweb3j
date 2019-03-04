package org.web3j.protocol.core.methods.request;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.exceptions.MessageDecodingException;
import org.web3j.utils.Numeric;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BigIntegerDeserializer extends JsonDeserializer<BigInteger> {

	@Override
	public BigInteger deserialize(
        JsonParser jsonParser,
        DeserializationContext deserializationContext) throws IOException {
		
		String value = jsonParser.getText();
		return BigIntegerDeserializer.Parse(value);
	}
	
	public static BigInteger Parse(String value) throws IOException {
			
			try {
				if (Numeric.containsHexPrefix(value)) {
					return new BigInteger(value.substring(2), 16);
				} else if (value.contains("e+")) {
					return new BigDecimal(value).toBigInteger();
				} else {
					return new BigInteger(value);
				}
	        } catch (NumberFormatException e) {
	            throw new MessageDecodingException("Negative ", e);
	        }
	}
}
