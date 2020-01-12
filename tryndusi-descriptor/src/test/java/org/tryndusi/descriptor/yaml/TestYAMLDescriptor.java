package org.tryndusi.descriptor.yaml;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;
import org.tryndusi.descriptor.yaml.Descriptor.Item;

public class TestYAMLDescriptor {

    @Test
    public void shouldStringify() throws IOException, URISyntaxException {
        final String expected = new String(Files.readAllBytes(Paths.get(getClass().getResource("/foo.yaml").toURI())))
                .replaceAll("\\r\\n", "\n");

        final Descriptor descriptor = new Descriptor();
        descriptor.setFoo("foo");
        descriptor.setBar("bar");
        descriptor.setBaz("baz");
        descriptor.setItems(new ArrayList<>());
        descriptor.getItems().add(Item.of("qux1", "quux1"));
        descriptor.getItems().add(Item.of("qux2", "quux2"));

        final YAMLDescriptor yamlDescriptor = new YAMLDescriptor();
        assertThat(yamlDescriptor.stringify(descriptor), is(expected));
    }

    @Test
    public void shouldObjectify() throws IOException, URISyntaxException {
        final Descriptor expected = new Descriptor();
        expected.setFoo("foo");
        expected.setBar("bar");
        expected.setBaz("baz");
        expected.setItems(new ArrayList<>());
        expected.getItems().add(Item.of("qux1", "quux1"));
        expected.getItems().add(Item.of("qux2", "quux2"));

        final byte[] bytes = Files.readAllBytes(Paths.get(getClass().getResource("/foo.yaml").toURI()));
        final YAMLDescriptor yamlDescriptor = new YAMLDescriptor();
        assertThat(yamlDescriptor.objectify(new ByteArrayInputStream(bytes), Descriptor.class), is(expected));
    }

    @Test
    public void shouldObjectifyPartially() throws IOException, URISyntaxException {
        final byte[] bytes = Files.readAllBytes(Paths.get(getClass().getResource("/foo.yaml").toURI()));
        final YAMLDescriptor yamlDescriptor = new YAMLDescriptor();
        final PartialDescriptor partial = yamlDescriptor.objectify(new ByteArrayInputStream(bytes),
                PartialDescriptor.class);

        assertThat("foo", is(partial.getFoo()));
        assertThat("bar", is(partial.getBar()));
        assertThat("baz", is(partial.getBaz()));
    }
}
