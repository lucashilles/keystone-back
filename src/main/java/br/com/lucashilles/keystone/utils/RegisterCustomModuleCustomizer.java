package br.com.lucashilles.keystone.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class RegisterCustomModuleCustomizer implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        SimpleModule module = new SimpleModule("JacksonDate");

        /// Serializer
        module.addSerializer(Date.class, new JacksonDateSerializer());

        /// Deserializer
        module.addDeserializer(Date.class, new JacksonDateDeserializer());

        objectMapper.registerModule(module);
    }

}
