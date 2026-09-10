package io.trogonstack.eventstore.client.misc;

import io.trogonstack.eventstore.client.ConnectionStringParsingException;
import io.trogonstack.eventstore.client.TrogonEventStoreConnectionString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ParseInvalidConnectionStringTests {
    public static Stream<Arguments> invalidConnectionStrings() {
        return Stream.of(
                Arguments.of("localhost"),
                Arguments.of("https://example.com/"),
                Arguments.of("trogon-invalid+discovery://localhost"),
                Arguments.of("trogon-invalid://my:great@username:UyeXx8$^PsOo4jG88FlCauR1Coz25q@host?nodePreference=follower&tlsVerifyCert=false"),
                Arguments.of("trogon-invalid://host1,host2:200:300?tlsVerifyCert=false"),
                Arguments.of("trogon-invalid://localhost/&tlsVerifyCert=false"),
                Arguments.of("trogon-invalid://localhost?tlsVerifyCert=false?nodePreference=follower"),
                Arguments.of("trogon-invalid://localhost?tlsVerifyCert=false&nodePreference=any"),
                Arguments.of("trogon-invalid://localhost?tlsVerifyCert=if you feel like it"),
                Arguments.of("trogon-invalid://localhost?keepAliveInterval=-3"),
                Arguments.of("trogon-invalid://localhost?keepAliveInterval=sdfksjsfl"),
                Arguments.of("trogon-invalid://localhost?keepAliveTimeout=sdfksjsfl"),
                Arguments.of("trogon-invalid://localhost?keepAliveTimeout=-3"),
                Arguments.of("trogon-invalid://localhost?nodePreference=read_only_replica"),
                Arguments.of("trogon-invalid://localhost?userCertFile=/path/to/cert"),
                Arguments.of("trogon-invalid://localhost?userKeyFile=/path/to/key")
       );
    }

    @ParameterizedTest
    @MethodSource("invalidConnectionStrings")
    public void test(String input) throws ConnectionStringParsingException {
        Assertions.assertThrows(RuntimeException.class, () -> {
            TrogonEventStoreConnectionString.parseOrThrow(input);
        });
    }
}
