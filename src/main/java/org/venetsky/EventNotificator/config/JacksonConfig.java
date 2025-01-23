package org.venetsky.EventNotificator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.venetsky.EventNotificator.notification.domain.FieldChange;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule s = new SimpleModule();
        s.addSerializer(FieldChange.class, new FieldChangeSerializer());
        objectMapper.registerModule(s);
        return objectMapper;
    }

}
