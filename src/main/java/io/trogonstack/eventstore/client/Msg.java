package io.trogonstack.eventstore.client;

interface Msg {
    void accept(ConnectionService handler);
}
