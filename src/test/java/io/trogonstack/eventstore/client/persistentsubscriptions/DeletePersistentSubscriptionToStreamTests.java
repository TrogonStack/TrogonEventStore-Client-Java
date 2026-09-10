package io.trogonstack.eventstore.client.persistentsubscriptions;

import io.trogonstack.eventstore.client.ConnectionAware;
import io.trogonstack.eventstore.client.TrogonEventStorePersistentSubscriptionsClient;
import org.junit.jupiter.api.Test;

public interface DeletePersistentSubscriptionToStreamTests extends ConnectionAware {
    @Test
    default void testDeletePersistentSub() throws Throwable {
        TrogonEventStorePersistentSubscriptionsClient client = getDefaultPersistentSubscriptionClient();
        String streamName = generateName();
        String groupName = generateName();

        client.createToStream(streamName, groupName)
                .get();

        client.deleteToStream(streamName, groupName)
                .get();
    }

    @Test
    default void testDeletePersistentSubToAll() throws Throwable {
        TrogonEventStorePersistentSubscriptionsClient client = getDefaultPersistentSubscriptionClient();
        String groupName = generateName();

        client.createToAll(groupName)
                .get();

        client.deleteToAll(groupName)
                .get();
    }
}
