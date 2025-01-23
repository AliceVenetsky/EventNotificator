package org.venetsky.EventNotificator.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.venetsky.EventNotificator.notification.domain.FieldChange;

import java.io.IOException;

public class FieldChangeSerializer extends JsonSerializer<FieldChange> {
    @Override
    public void serialize(
            FieldChange fieldChange,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        String stringValue = fieldChange.toString();
        if (stringValue != null && !stringValue.isEmpty() && !stringValue.equals("null")) {
            jsonGenerator.writeString(stringValue);
        } else {
            jsonGenerator.writeNull();
        }
    }
}
