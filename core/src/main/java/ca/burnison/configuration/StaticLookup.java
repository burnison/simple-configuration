package ca.burnison.configuration;

import javax.annotation.Nonnull;

final class StaticLookup<T> implements Lookup<T> {
    private final String name;
    private final Result<T> result;

    StaticLookup(@Nonnull final Lookup<T> source) {
        this.name = source.name();
        this.result = source.lookup();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Result<T> lookup() {
        return this.result;
    }

    @Override
    public void changed() {
    }
}
