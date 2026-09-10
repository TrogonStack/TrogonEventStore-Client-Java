package io.trogonstack.eventstore.client;

@FunctionalInterface
public interface SubscriptionTracingCallback {
    void trace(String subscriptionId, RecordedEvent event, Runnable action);
}
