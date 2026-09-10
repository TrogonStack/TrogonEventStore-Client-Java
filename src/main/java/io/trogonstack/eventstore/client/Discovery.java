package io.trogonstack.eventstore.client;

import java.util.concurrent.CompletableFuture;

public interface Discovery {
    CompletableFuture<Void> run(ConnectionState state);
}