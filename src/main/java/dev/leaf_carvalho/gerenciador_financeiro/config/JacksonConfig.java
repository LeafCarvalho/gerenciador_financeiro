package dev.leaf_carvalho.gerenciador_financeiro.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    @Bean
    ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.simpleDateFormat("dd/MM/yyyy");
        builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return builder.build();
    }
}
