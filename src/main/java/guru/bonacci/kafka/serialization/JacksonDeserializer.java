package guru.bonacci.kafka.serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDeserializer<T> implements Deserializer<T> {

    private final Class<T> type;
    private final ObjectMapper objectMapper;

    
    public JacksonDeserializer(Class<T> type) {
        this(type, ObjectMapperProducer.get());
    }

    public JacksonDeserializer(Class<T> type, ObjectMapper objectMapper) {
        this.type = type;
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO supply jackson configuration options
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) 
            return null;

        try (InputStream is = new ByteArrayInputStream(data)) {
            return objectMapper.readValue(is, type);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {
    }
    
    Class<T> getType() {
    	return type;
    }
}