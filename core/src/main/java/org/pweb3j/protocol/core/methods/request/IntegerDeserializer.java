package org.pweb3j.protocol.core.methods.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.pweb3j.exceptions.MessageDecodingException;
import org.pweb3j.utils.Numeric;

import java.io.IOException;

public class IntegerDeserializer  extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {

        String value = jsonParser.getText();
        return IntegerDeserializer.Parse(value);
    }

    public static Integer Parse(String value) throws IOException {
        try {
            if (Numeric.containsHexPrefix(value)) {
                return Integer.parseInt(value.substring(2), 16);
            } else {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            throw new MessageDecodingException("Negative ", e);
        }
    }
}