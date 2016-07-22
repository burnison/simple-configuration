package ca.burnison.configuration;

import java.util.function.Predicate;
import javax.annotation.Nonnull;

final class ValidatingLookup<T> implements Lookup<T> {
    private final Lookup<T> source;
    private final Predicate<T> predicate;

    ValidatingLookup(@Nonnull final Lookup<T> source, @Nonnull final Predicate<T> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    @Override
    public String name() {
        return this.source.name();
    }

    @Override
    public Result<T> lookup() {
        /*
         * If the current value is already missing, don't apply the filter. By
         * doing this, the caller can differentiate between a property that is
         * currently missing vs. a property that becomes invalid after the
         * filter.
         */
        final Result<T> current = this.source.lookup();
        if (!current.isPresent()) {
            return current;
        }
        final Result<T> result = current.filter(this.predicate);
        if (result.isPresent()) {
            return result;
        }
        throw new PropertyInvalidException("Property " + this.name() + " is invalid.");
    }

    @Override
    public void changed() {
        this.source.changed();
    }
}
