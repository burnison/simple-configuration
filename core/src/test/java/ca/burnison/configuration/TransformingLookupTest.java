package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class TransformingLookupTest {

    @Test
    public void lookup() {
        Assert.assertEquals("noop", new TransformingLookup<>(FakeLookup.using("test"), s -> "noop").lookup().get());
        Assert.assertEquals("noop", new TransformingLookup<>(FakeLookup.using(null), s -> "noop").lookup().get());
        Assert.assertNull(new TransformingLookup<>(FakeLookup.using(), s -> "noop").lookup().get());
    }

    @Test
    public void present() {
        Assert.assertTrue(new TransformingLookup<>(FakeLookup.using("test"), s -> "noop").lookup().isPresent());
        Assert.assertTrue(new TransformingLookup<>(FakeLookup.using(null), s -> "noop").lookup().isPresent());
        Assert.assertFalse(new TransformingLookup<>(FakeLookup.using(), s -> "noop").lookup().isPresent());
    }
}
