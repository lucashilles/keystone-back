package br.com.lucashilles.keystone.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

public class JacksonDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        if (date != null) {
            generator.writeNumber(date.getTime());
        } else {
            generator.writeNull();
        }
    }
}
