package org.tryndusi.discovery.jimfs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.tryndusi.common.Args;
import org.tryndusi.descriptor.Descriptor;
import org.tryndusi.descriptor.DescriptorBuilder;
import org.tryndusi.discovery.Discoverable;
import org.tryndusi.discovery.Registry;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

public class JimfsRegistry implements Registry {

    private final FileSystem fs;
    private final Descriptor mapper;

    public JimfsRegistry() {
        fs = Jimfs.newFileSystem(Configuration.unix());
        mapper = new DescriptorBuilder().build();
    }

    @Override
    public Optional<Discoverable> discover(String discoveryPath) {
        return discover(discoveryPath, Discoverable.class);
    }

    @Override
    public <T extends Discoverable> Optional<T> discover(String discoveryPath, Class<T> type) {
        final Path path = fs.getPath(addRootIfMissing(discoveryPath));
        if (Files.exists(path)) {
            try {
                return Optional.ofNullable(mapper.objectify(new ByteArrayInputStream(Files.readAllBytes(path)), type));
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read published service '" + discoveryPath + "'.", e);
            }
        }
        return Optional.empty();
    }

    @Override
    public void publish(Discoverable discoverable) {
        Args.notNull(discoverable.getDiscoveryPath(), "Discoverable discovery path required.");
        Args.notNull(discoverable.getHostname(), "Discoverable hostname required.");
        Args.notNull(discoverable.getPort(), "Discoverable port required.");

        final String discoveryPath = addRootIfMissing(discoverable.getDiscoveryPath());
        final Path path = fs.getPath(discoveryPath);
        if (Files.exists(path)) {
            throw new IllegalStateException(
                    "Service already published at discoverable path '" + path + "' (unpublish first).");
        }
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            Files.write(path, mapper.stringify(discoverable).getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("Service cannot be published.", e);
        }
    }

    @Override
    public void unpublish(String discoveryPath) {
        Args.notNull(discoveryPath, "Discoverable discovery path required.");

        discoveryPath = addRootIfMissing(discoveryPath);
        final Path path = fs.getPath(discoveryPath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new IllegalStateException("Service cannot be unpublished.", e);
        }
    }

    private String addRootIfMissing(String discoveryPath) {
        if (!discoveryPath.startsWith("/")) {
            return "/" + discoveryPath;
        } else {
            return discoveryPath;
        }
    }
}
