package org.pweb3j.protocol.core.methods.response;

import org.pweb3j.protocol.core.Response;
import org.pweb3j.utils.Numeric;

public class TdmGetCurrentEpochNumber extends Response<String> {
	public int getCurrentEpochNumber() {
		String result = getResult();
		int intRet = -1;
		if (Numeric.containsHexPrefix(result)) {
			intRet = Numeric.decodeQuantity(result).intValue();
		} else {
			intRet = Integer.valueOf(result).intValue();
		}

		return intRet;
    }
}
