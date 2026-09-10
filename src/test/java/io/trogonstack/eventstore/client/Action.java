package io.trogonstack.eventstore.client;

@FunctionalInterface
public interface Action<A> {
    A run() throws Exception;
}
