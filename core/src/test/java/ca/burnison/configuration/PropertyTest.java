package ca.burnison.configuration;

public final class PropertyTest extends PropertyTester<Object, PropertyTest.SimpleProperty> {
    private static final Object A = new Object();
    private static final Object B = new Object();

    static final class SimpleProperty extends Property<Object, SimpleProperty> {
        SimpleProperty(final String name, final Lookup<Object> lookup) {
            super(name, lookup);
        }
    }

    @Override
    protected Object valueA() {
        return A;
    }

    @Override
    protected Object valueB() {
        return B;
    }

    @Override
    protected SimpleProperty create(final Object value) {
        return new SimpleProperty("test", FakeLookup.using(value));
    }

    @Override
    protected SimpleProperty create() {
        return new SimpleProperty("test", FakeLookup.using());
    }
}
