package io.trogonstack.eventstore.client.samples.appending_events;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.trogonstack.eventstore.client.*;
import io.trogonstack.eventstore.client.samples.TestEvent;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AppendingEvents {
    private static void appendToStream(TrogonEventStoreClient client) throws ExecutionException, InterruptedException, JsonProcessingException {
        // region append-to-stream
        ObjectMapper objectMapper = new ObjectMapper();

        EventData eventData = EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        "some-event",
                        objectMapper.writeValueAsBytes(new TestEvent("1", "some value"))
                )
                .build();

        AppendToStreamOptions options = AppendToStreamOptions.get()
                .streamState(StreamState.noStream());

        client.appendToStream("some-stream", options, eventData)
                .get();
        // endregion append-to-stream
    }

    private static void appendWithSameId(TrogonEventStoreClient client) throws ExecutionException, InterruptedException, JsonProcessingException {
        // region append-duplicate-event
        ObjectMapper objectMapper = new ObjectMapper();

        EventData eventData = EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        "some-event",
                        objectMapper.writeValueAsBytes(new TestEvent("1", "some value")))
                .build();

        AppendToStreamOptions options = AppendToStreamOptions.get()
                .streamState(StreamState.any());

        client.appendToStream("same-event-stream", options, eventData)
                .get();

        // attempt to append the same event again
        client.appendToStream("same-event-stream", options, eventData)
                .get();
        // endregion append-duplicate-event
    }

    private static void appendWithNoStream(TrogonEventStoreClient client) throws ExecutionException, InterruptedException, JsonProcessingException {
        // region append-with-no-stream
        ObjectMapper objectMapper = new ObjectMapper();

        EventData eventDataOne = EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        "some-event",
                        objectMapper.writeValueAsBytes(new TestEvent("1", "some value")))
                .build();

        EventData eventDataTwo = EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        "some-event",
                        objectMapper.writeValueAsBytes(new TestEvent("2", "some other value")))
                .build();

        AppendToStreamOptions options = AppendToStreamOptions.get()
                .streamState(StreamState.noStream());

        client.appendToStream("no-stream-stream", options, eventDataOne)
                .get();

        // attempt to append the same event again
        client.appendToStream("no-stream-stream", options, eventDataTwo)
                .get();
        // endregion append-with-no-stream
    }

    private static void appendWithConcurrencyCheck(TrogonEventStoreClient client) throws ExecutionException, InterruptedException, JsonProcessingException {
        // region append-with-concurrency-check
        ObjectMapper objectMapper = new ObjectMapper();

        ReadStreamOptions readStreamOptions = ReadStreamOptions.get()
                .forwards()
                .fromStart();

        ReadResult result = client.readStream("concurrency-stream", readStreamOptions)
                .get();

        EventData clientOneData = EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        "some-event",
                        objectMapper.writeValueAsBytes(new TestEvent("1", "clientOne")))
                .build();

        EventData clientTwoData = EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        "some-event",
                        objectMapper.writeValueAsBytes(new TestEvent("2", "clientTwo")))
                .build();


        AppendToStreamOptions options = AppendToStreamOptions.get()
                .streamRevision(result.getLastStreamPosition());

        client.appendToStream("concurrency-stream", options, clientOneData)
                .get();

        client.appendToStream("concurrency-stream", options, clientTwoData)
                .get();
        // endregion append-with-concurrency-check
    }

    public void appendOverridingUserCredentials(TrogonEventStoreClient client) throws ExecutionException, InterruptedException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        EventData eventData = EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        "some-event",
                        objectMapper.writeValueAsBytes(new TestEvent("1", "some value")))
                .build();
        //region overriding-user-credentials
        UserCredentials credentials = new UserCredentials("admin", "changeit");

        AppendToStreamOptions options = AppendToStreamOptions.get()
                .authenticated(credentials);

        client.appendToStream("some-stream", options, eventData)
                .get();
        // endregion overriding-user-credentials
    }
}
