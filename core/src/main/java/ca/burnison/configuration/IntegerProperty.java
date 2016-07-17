package ca.burnison.configuration;

public final class IntegerProperty extends Property<Integer, IntegerProperty> {

    IntegerProperty(final String name, final Lookup<Integer> lookup) {
        super(name, lookup);
    }

    public int intValue() {
        return this.get().intValue();
    }
}
