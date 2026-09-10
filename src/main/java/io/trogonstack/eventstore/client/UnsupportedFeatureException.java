package io.trogonstack.eventstore.client;

/**
 * A request not supported by the targeted TrogonEventStore node was sent.
 */
public class UnsupportedFeatureException extends RuntimeException {
    UnsupportedFeatureException(){
        super("Unsupported feature exception");
    }
}
