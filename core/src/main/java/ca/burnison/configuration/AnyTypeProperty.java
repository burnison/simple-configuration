package ca.burnison.configuration;

public final class AnyTypeProperty<T> extends Property<T, AnyTypeProperty<T>> {
    AnyTypeProperty(final String name, final Lookup<T> lookup) {
        super(name, lookup);
    }
}
