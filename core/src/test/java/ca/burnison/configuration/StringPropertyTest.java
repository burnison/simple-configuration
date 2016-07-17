package ca.burnison.configuration;

public final class StringPropertyTest extends PropertyTester<String, StringProperty> {

    @Override
    protected String valueA() {
        return "a";
    }

    @Override
    protected String valueB() {
        return "b";
    }

    @Override
    protected StringProperty create(final String value) {
        return new StringProperty("test", FakeLookup.using(value));
    }

    @Override
    protected StringProperty create() {
        return new StringProperty("test", FakeLookup.using());
    }
}
