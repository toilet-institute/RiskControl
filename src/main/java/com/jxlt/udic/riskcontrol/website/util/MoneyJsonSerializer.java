package com.jxlt.udic.riskcontrol.website.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.money.Money;

import java.io.IOException;

/**
 * 解析money为String
 *
 * @author 魏巍
 * @since 2019/4/4 15:14
 */
public class MoneyJsonSerializer extends JsonSerializer<Money> {
    @Override
    public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(money.getAmount().doubleValue());
    }
}
