package guru.bonacci.kafka.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SmilingSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper;

    public SmilingSerializer() {
        this(ObjectMapperProducer.get());
    }

    public SmilingSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    	//TODO when jackson config can be supplied
    }

    @Override
    public byte[] serialize(String topic, T data) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            objectMapper.writeValue(output, data);
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }
}