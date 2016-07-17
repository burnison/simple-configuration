package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class BooleanPropertyTest extends PropertyTester<Boolean, BooleanProperty> {

    @Test
    public void booleanValue() {
        Assert.assertEquals(true, create(true).booleanValue());
    }

    @Override
    protected Boolean valueA() {
        return Boolean.TRUE;
    }

    @Override
    protected Boolean valueB() {
        return Boolean.FALSE;
    }

    @Override
    protected BooleanProperty create(final Boolean value) {
        return new BooleanProperty("test", FakeLookup.using(value));
    }

    @Override
    protected BooleanProperty create() {
        return new BooleanProperty("test", FakeLookup.using());
    }
}
