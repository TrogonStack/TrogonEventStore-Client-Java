package io.trogonstack.eventstore.client;

import java.util.Optional;

interface EventFilter {
    PrefixFilterExpression[] getPrefixFilterExpressions();

    RegularFilterExpression getRegularFilterExpression();

    Optional<Integer> getMaxSearchWindow();
}
