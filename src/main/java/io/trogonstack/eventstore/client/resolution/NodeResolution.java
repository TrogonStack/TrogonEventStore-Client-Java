package io.trogonstack.eventstore.client.resolution;

import java.net.InetSocketAddress;
import java.util.List;

public interface NodeResolution {
    List<InetSocketAddress> resolve();
}
