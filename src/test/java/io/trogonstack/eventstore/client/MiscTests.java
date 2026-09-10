package io.trogonstack.eventstore.client;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("io.trogonstack.eventstore.client.misc")
@SelectClasses({SubscriptionStreamConsumerTests.class, LeaderRedirectUnitTest.class})
public class MiscTests {}
