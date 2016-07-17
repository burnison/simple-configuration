package ca.burnison.configuration;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.checkerframework.dataflow.qual.SideEffectFree;

interface Lookup<T> {
    final class Result<T> {
        private static final Result<?> MISSING = new Result<>(null);

        private final T value;

        private Result(final T value) {
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        static <T> Result<T> with() {
            return (Result<T>) MISSING;
        }

        static <T> Result<T> with(final T value) {
            return new Result<>(value);
        }

        boolean isPresent() {
            return this != MISSING;
        }

        T get() {
            return this.value;
        }

        T orElse(final T defaultValue) {
            if (this == MISSING) {
                return defaultValue;
            }
            return this.value;
        }

        T orElseGet(final Supplier<? extends T> supplier) {
            if (this == MISSING) {
                return supplier.get();
            }
            return this.value;
        }

        Result<T> filter(final Predicate<T> predicate) {
            if (this == MISSING) {
                return this;
            }
            return predicate.test(this.value) ? this : with();
        }

        <O> Result<O> map(final Function<T, O> f) {
            if (this == MISSING) {
                // Avoid the cast and warning suppression.
                return with();
            }
            return with(f.apply(this.value));
        }
    }

    @SideEffectFree
    String name();

    /**
     * Gets the current value regardless of presence.
     */
    @SideEffectFree
    Result<T> lookup();

    /**
     * A callback used to identify that the underlying property has changed in
     * some way. Implementations <b>must</b> be thread safe.
     */
    void changed();
}
