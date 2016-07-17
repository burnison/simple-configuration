package ca.burnison.configuration;

public final class LongProperty extends Property<Long, LongProperty> {

    LongProperty(final String name, final Lookup<Long> lookup) {
        super(name, lookup);
    }

    public long longValue() {
        return this.get().longValue();
    }
}
