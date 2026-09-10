package io.trogonstack.eventstore.client.resolution;

import io.trogonstack.eventstore.client.Endpoint;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixedSeedsNodeResolution implements NodeResolution {
    private final Endpoint[] seeds;

    public FixedSeedsNodeResolution(Endpoint[] seeds) {
        this.seeds = seeds;
    }

    @Override
    public List<InetSocketAddress> resolve() {
        List<InetSocketAddress> addresses = new ArrayList<>(seeds.length);

        for (Endpoint seed : seeds)
            addresses.add(new InetSocketAddress(seed.getHost(), seed.getPort()));

        return addresses;
    }
}
