package guru.bonacci.kafka.serialization;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonSerializer<T> implements Serializer<T> {

	private final ObjectMapper mapper;

	public JacksonSerializer() {
		this(ObjectMapperSupplier.get());
	}

	public JacksonSerializer(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO isKey?
		// TODO supply jackson configuration options?
	}

	@Override
	public byte[] serialize(String topic, T data) {
		if (data == null)
			return null;

		try {
			return mapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			throw new SerializationException(e);
		}
	}

	@Override
	public void close() {
	}
}