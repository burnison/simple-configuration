package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class IntegerPropertyTest extends PropertyTester<Integer, IntegerProperty> {

    @Test
    public void intValue() {
        Assert.assertEquals(5, create(5).intValue());
    }

    @Override
    protected Integer valueA() {
        return 1;
    }

    @Override
    protected Integer valueB() {
        return 5;
    }

    @Override
    protected IntegerProperty create(final Integer value) {
        return new IntegerProperty("test", FakeLookup.using(value));
    }

    @Override
    protected IntegerProperty create() {
        return new IntegerProperty("test", FakeLookup.using());
    }
}
