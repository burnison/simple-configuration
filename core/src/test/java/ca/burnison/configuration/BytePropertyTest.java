package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class BytePropertyTest extends PropertyTester<Byte, ByteProperty> {

    @Test
    public void byteValue() {
        Assert.assertEquals((byte) 5, create((byte) 5).byteValue());
    }

    @Override
    protected Byte valueA() {
        return (byte) 5;
    }

    @Override
    protected Byte valueB() {
        return (byte) 9;
    }

    @Override
    protected ByteProperty create(final Byte value) {
        return new ByteProperty("test", FakeLookup.using(value));
    }

    @Override
    protected ByteProperty create() {
        return new ByteProperty("test", FakeLookup.using());
    }
}
