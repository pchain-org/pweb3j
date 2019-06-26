package org.pweb3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.protocol.core.methods.request.BigIntegerDeserializer;

public class ChainGetBlockReward extends Response<String> {

	public BigInteger getReward() {
		
		try {
			return BigIntegerDeserializer.Parse(getResult());
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		
		return null;
	}
}
