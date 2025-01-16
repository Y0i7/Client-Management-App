package com.Client.Managment.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Bean
    public ObjectMapper objectMapper(){
        SimpleModule module = new SimpleModule();
        module.addSerializer(java.time.LocalDateTime.class,new LocalDateTimeSerializer(dateTimeFormatter));

        return Jackson2ObjectMapperBuilder.json()
                .modulesToInstall(new ParameterNamesModule())
                .modules(module)
                .build().enable(SerializationFeature.WRAP_ROOT_VALUE);
    }
}
