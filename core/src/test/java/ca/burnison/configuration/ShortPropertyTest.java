package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class ShortPropertyTest extends PropertyTester<Short, ShortProperty> {

    @Test
    public void shortValue() {
        Assert.assertEquals((short) 5, create((short) 5).shortValue());
    }

    @Override
    protected Short valueA() {
        return (short) 3;
    }

    @Override
    protected Short valueB() {
        return (short) 5;
    }

    @Override
    protected ShortProperty create(final Short value) {
        return new ShortProperty("test", FakeLookup.using(value));
    }

    @Override
    protected ShortProperty create() {
        return new ShortProperty("test", FakeLookup.using());
    }
}
