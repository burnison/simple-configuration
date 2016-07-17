package ca.burnison.configuration;

import java.util.UUID;

final class FakeLookup<T> implements Lookup<T> {
    private final String name = UUID.randomUUID().toString();
    private Result<T> result;

    private FakeLookup(final Result<T> result) {
        this.result = result;
    }

    static <T> FakeLookup<T> using(final T value) {
        return new FakeLookup<>(Result.with(value));
    }

    static <T> FakeLookup<T> using() {
        return new FakeLookup<>(Result.with());
    }

    FakeLookup<T> update(final T t) {
        this.result = Result.with(t);
        return this;
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
