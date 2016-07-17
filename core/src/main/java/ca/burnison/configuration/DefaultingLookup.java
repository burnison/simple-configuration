package ca.burnison.configuration;

import javax.annotation.Nonnull;

final class DefaultingLookup<T> implements Lookup<T> {
    private final T defaultIfAbsent;
    private final Lookup<T> source;

    DefaultingLookup(@Nonnull final Lookup<T> source, @Nonnull final T defaultIfAbsent) {
        this.defaultIfAbsent = defaultIfAbsent;
        this.source = source;
    }

    @Override
    public String name() {
        return this.source.name();
    }

    @Override
    public Result<T> lookup() {
        final Result<T> current = this.source.lookup();
        if (current.isPresent()) {
            return current;
        }
        return Result.with(this.defaultIfAbsent);
    }

    @Override
    public void changed() {
        this.source.changed();
    }
}
