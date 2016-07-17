package ca.burnison.configuration;

public final class StringProperty extends Property<String, StringProperty> {
    StringProperty(final String name, final Lookup<String> lookup) {
        super(name, lookup);
    }
}
