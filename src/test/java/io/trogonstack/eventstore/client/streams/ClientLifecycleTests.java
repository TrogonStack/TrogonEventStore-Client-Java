package io.trogonstack.eventstore.client.streams;

import io.trogonstack.eventstore.client.ConnectionAware;
import io.trogonstack.eventstore.client.ConnectionShutdownException;
import io.trogonstack.eventstore.client.TrogonEventStoreClient;
import io.trogonstack.eventstore.client.TrogonEventStoreClientSettings;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public interface ClientLifecycleTests extends ConnectionAware {
    @Test
    default void testProvidesRunningStatus() {
        TrogonEventStoreClient client = getDatabase().newClient();

        assertFalse(client.isShutdown());
    }

    @Test
    default void testProvidesShutdownStatusAfterManualShutdown() throws Throwable {
        TrogonEventStoreClient client = getDatabase().newClient();

        client.shutdown().get();

        assertTrue(client.isShutdown());
    }

    @Test
    default void testProvidesShutdownStatusAfterAutomaticShutdown() throws Throwable {
        TrogonEventStoreClientSettings settings = TrogonEventStoreClientSettings.builder()
                .addHost("unknown.host.name", 2113)
                .buildConnectionSettings();
        TrogonEventStoreClient client = TrogonEventStoreClient.create(settings);

        try {
            client.readAll().get();
            fail();
        } catch (ExecutionException ex) {
            assertInstanceOf(ConnectionShutdownException.class, ex.getCause());
        }
        assertTrue(client.isShutdown());
    }
}
