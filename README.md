# TrogonEventStore Java Client

[![CI](https://github.com/TrogonStack/TrogonEventStore-Client-Java/actions/workflows/ci.yml/badge.svg)](https://github.com/TrogonStack/TrogonEventStore-Client-Java/actions/workflows/ci.yml)

The Java client for [TrogonEventStore](https://github.com/TrogonStack/TrogonEventStore), compatible with Java 8 and newer.

## Install

Packages are published to GitHub Packages under `io.trogonstack:trogon-eventstore-client`.

```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/TrogonStack/TrogonEventStore-Client-Java")
        credentials {
            username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
            password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation "io.trogonstack:trogon-eventstore-client:VERSION"
}
```

GitHub Packages requires authentication. Use a classic personal access token with `read:packages` outside GitHub Actions. Do not commit credentials or a credential-bearing Gradle properties file.

## Connect

```java
import io.trogonstack.eventstore.client.TrogonEventStoreClient;
import io.trogonstack.eventstore.client.TrogonEventStoreClientSettings;
import io.trogonstack.eventstore.client.TrogonEventStoreConnectionString;

TrogonEventStoreClientSettings settings = TrogonEventStoreConnectionString.parseOrThrow(
    "trogon-eventstore://localhost:2113?tls=false"
);
TrogonEventStoreClient client = TrogonEventStoreClient.create(settings);
```

Use `trogon-eventstore+discover://` for DNS or gossip discovery.

## Development

The project uses the checked-in Gradle wrapper.

```shell
./gradlew clean check
```

Integration tests use `ghcr.io/trogonstack/trogoneventstore:ci` by default. Override it with `TROGON_EVENTSTORE_IMAGE`.

## License

Licensed under Apache-2.0. See [LICENSE](LICENSE).
