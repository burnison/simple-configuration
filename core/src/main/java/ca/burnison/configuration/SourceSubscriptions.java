package ca.burnison.configuration;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

final class SourceSubscriptions {
    private final Set<Lookup<?>> lookups = Collections.newSetFromMap(new ConcurrentHashMap<>());

    void subscribe(final Lookup<?> lookup) {
        this.lookups.add(Objects.requireNonNull(lookup));
    }

    void unsubscribe(final Lookup<?> lookup) {
        this.lookups.remove(Objects.requireNonNull(lookup));
    }

    void notify(final String name) {
        for (final Lookup<?> lookup : this.lookups) {
            if (lookup.name().equals(name)) {
                lookup.changed();
            }
        }
    }
}
