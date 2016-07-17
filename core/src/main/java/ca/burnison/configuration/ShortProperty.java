package ca.burnison.configuration;

public final class ShortProperty extends Property<Short, ShortProperty> {

    ShortProperty(final String name, final Lookup<Short> lookup) {
        super(name, lookup);
    }

    public long shortValue() {
        return this.get().longValue();
    }
}
