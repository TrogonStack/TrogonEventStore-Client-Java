package io.trogonstack.eventstore.client.persistentsubscriptions;

import io.trogonstack.eventstore.client.*;
import org.junit.jupiter.api.Test;

public interface CreatePersistentSubscriptionTests extends ConnectionAware {
    @Test
    default void testCreatePersistentSub() throws Throwable {
        TrogonEventStorePersistentSubscriptionsClient client = getDefaultPersistentSubscriptionClient();

        client.createToStream(generateName(), generateName())
                .get();

        client.createToStream(generateName(), generateName(), CreatePersistentSubscriptionToStreamOptions.get().startFrom(2))
                .get();
    }

    @Test
    default void testCreatePersistentSubToAll() throws Throwable {
        TrogonEventStorePersistentSubscriptionsClient client = getDefaultPersistentSubscriptionClient();

        client.createToAll(generateName())
                .get();

        client.createToAll(generateName(), CreatePersistentSubscriptionToAllOptions.get().startFrom(1, 2))
                .get();
    }
}
