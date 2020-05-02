package guru.bonacci.kafka.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SmilingSerde<T> implements Serde<T> {

	private final SmilingSerializer<T> serializer;
	private final SmilingDeserializer<T> deserializer;

	public SmilingSerde(Class<T> type) {
		this(type, ObjectMapperProducer.get());
	}

	public SmilingSerde(Class<T> type, ObjectMapper objectMapper) {
		this.serializer = new SmilingSerializer<T>(objectMapper);
		this.deserializer = new SmilingDeserializer<T>(type, objectMapper);
	}

	public static final <T> SmilingSerde<T> of(Class<T> cls) {
		return new SmilingSerde<>(cls);
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
    	//TODO when jackson config can be supplied
	}

	@Override
	public void close() {
		serializer.close();
		deserializer.close();
	}

	@Override
	public Serializer<T> serializer() {
		return serializer;
	}

	@Override
	public Deserializer<T> deserializer() {
		return deserializer;
	}
}