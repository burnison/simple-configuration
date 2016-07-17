package ca.burnison.configuration;

import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

final class CachingLookup<T> implements Lookup<T> {

    private static final class Cache<T> {
        private final Result<T> result;

        private Cache(final Lookup<T> source) {
            this.result = source.lookup();
        }
    }

    private final AtomicReference<Cache<T>> cache;
    private final Lookup<T> source;

    CachingLookup(@Nonnull final Lookup<T> source) {
        this.cache = new AtomicReference<>(new Cache<>(source));
        this.source = source;
    }

    @Override
    public String name() {
        return this.source.name();
    }

    @Override
    public Result<T> lookup() {
        return this.cache.get().result;
    }

    @Override
    public void changed() {
        this.cache.set(new Cache<T>(this.source));
        this.source.changed();
    }
}
