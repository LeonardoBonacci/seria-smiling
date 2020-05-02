package guru.bonacci.kafka.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

final class ObjectMapperProducer {

    private ObjectMapperProducer() {
    }

    static ObjectMapper get() {
        return new ObjectMapper(new SmileFactory());
    }
}