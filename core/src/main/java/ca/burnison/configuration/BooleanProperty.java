package ca.burnison.configuration;

public final class BooleanProperty extends Property<Boolean, BooleanProperty> {

    BooleanProperty(final String name, final Lookup<Boolean> lookup) {
        super(name, lookup);
    }

    public boolean booleanValue() {
        return this.get().booleanValue();
    }
}
