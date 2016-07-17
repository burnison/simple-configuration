package ca.burnison.configuration;

public final class AnyTypePropertyTest extends PropertyTester<Object, AnyTypeProperty<Object>> {
    @Override
    protected Object valueA() {
        return 33L;
    }

    @Override
    protected Object valueB() {
        return "foo";
    }

    @Override
    protected AnyTypeProperty<Object> create(final Object value) {
        return new AnyTypeProperty<>("test", FakeLookup.using(value));
    }

    @Override
    protected AnyTypeProperty<Object> create() {
        return new AnyTypeProperty<>("test", FakeLookup.using());
    }
}
