package guru.bonacci.kafka.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

final class ObjectMapperProducer {

    static ObjectMapper get() {
    	return get(false);
    }

    static ObjectMapper get(boolean smile) {
    	if (smile)
    		return new ObjectMapper(new SmileFactory());
    	else return new ObjectMapper();
    }
}