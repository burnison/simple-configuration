package ca.burnison.configuration;

import javax.annotation.Nonnull;

final class RequiringLookup<T> implements Lookup<T> {
    private final Lookup<T> source;

    RequiringLookup(@Nonnull final Lookup<T> source) {
        this.source = source;
    }

    @Override
    public String name() {
        return this.source.name();
    }

    @Override
    public Result<T> lookup() {
        final Result<T> current = this.source.lookup();
        if (!current.isPresent()) {
            throw new PropertyMissingException("Property " + this.name() + " is missing.");
        }
        return current;
    }

    @Override
    public void changed() {
        this.source.changed();
    }
}
