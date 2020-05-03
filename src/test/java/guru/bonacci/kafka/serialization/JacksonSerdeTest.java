package guru.bonacci.kafka.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.kafka.common.serialization.Serde;
import org.junit.jupiter.api.Test;

import guru.bonacci.kafka.serialization.model.Foo;

public class JacksonSerdeTest {

	@Test
	public void of() {
		Serde<Foo> serde = JacksonSerde.of(Foo.class);

		Foo expected = new Foo("first", "second");
		byte[] buffer = serde.serializer().serialize("topic", expected);
		Foo actual = serde.deserializer().deserialize("topic", buffer);
		assertNotNull(actual);
		assertEquals(expected.getFirstBar(), expected.getFirstBar());
		assertEquals(expected.getSecondBar(), expected.getSecondBar());
		serde.close();
	}
	
	@Test
	public void smile() {
		Serde<Foo> serde = JacksonSerde.of(Foo.class, true);

		Foo expected = new Foo("first", "second");
		byte[] buffer = serde.serializer().serialize("topic", expected);
		Foo actual = serde.deserializer().deserialize("topic", buffer);
		assertNotNull(actual);
		assertEquals(expected.getFirstBar(), expected.getFirstBar());
		assertEquals(expected.getSecondBar(), expected.getSecondBar());
		serde.close();
	}

}