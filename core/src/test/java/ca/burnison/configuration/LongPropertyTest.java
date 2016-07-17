package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class LongPropertyTest extends PropertyTester<Long, LongProperty> {

    @Test
    public void longValue() {
        Assert.assertEquals(5L, create(5L).longValue());
    }

    @Override
    protected Long valueA() {
        return 1L;
    }

    @Override
    protected Long valueB() {
        return 5L;
    }

    @Override
    protected LongProperty create(final Long value) {
        return new LongProperty("test", FakeLookup.using(value));
    }

    @Override
    protected LongProperty create() {
        return new LongProperty("test", FakeLookup.using());
    }
}
