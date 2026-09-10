package io.trogonstack.eventstore.client.samples.authentication;

import io.trogonstack.eventstore.client.TrogonEventStoreClient;
import io.trogonstack.eventstore.client.TrogonEventStoreClientSettings;
import io.trogonstack.eventstore.client.TrogonEventStoreConnectionString;

public class UserCertificate {
    private static void tracing() {
        // region client-with-user-certificates
        TrogonEventStoreClientSettings settings = TrogonEventStoreConnectionString
                .parseOrThrow("trogon-eventstore://admin:changeit@{endpoint}?tls=true&userCertFile={pathToCaFile}&userKeyFile={pathToKeyFile}");
        TrogonEventStoreClient client = TrogonEventStoreClient.create(settings);
        // endregion client-with-user-certificates
    }
}
