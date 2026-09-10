package io.trogonstack.eventstore.client;

import io.trogonstack.eventstore.client.proto.persistentsubscriptions.Persistent;
import io.trogonstack.eventstore.client.proto.shared.Shared;

class SubscribePersistentSubscriptionToAll extends AbstractSubscribePersistentSubscription {
    public SubscribePersistentSubscriptionToAll(GrpcClient connection, String group,
                                                SubscribePersistentSubscriptionOptions options,
                                                PersistentSubscriptionListener listener) {
        super(connection, group, options, listener);
    }

    @Override
    protected Persistent.ReadReq.Options.Builder createOptions() {
        return defaultReadOptions.clone()
                .setAll(Shared.Empty.newBuilder());
    }
}
