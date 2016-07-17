package ca.burnison.configuration;

import java.util.function.Function;
import javax.annotation.Nonnull;

final class TransformingLookup<F, T> implements Lookup<T> {
    private final Lookup<F> source;
    private final Function<F, T> function;

    TransformingLookup(@Nonnull final Lookup<F> source, @Nonnull final Function<F, T> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    public String name() {
        return this.source.name();
    }

    @Override
    public Result<T> lookup() {
        return this.source.lookup().map(this.function);
    }

    @Override
    public void changed() {
        this.source.changed();
    }
}
