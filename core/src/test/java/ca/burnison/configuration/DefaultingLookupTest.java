package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class DefaultingLookupTest {

    @Test
    public void lookup() {
        Assert.assertEquals("test", new DefaultingLookup<>(FakeLookup.using("test"), "none").lookup().get());
        Assert.assertNull(new DefaultingLookup<>(FakeLookup.using(null), "none").lookup().get());
        Assert.assertEquals("none", new DefaultingLookup<>(FakeLookup.using(), "none").lookup().get());
    }

    @Test
    public void present() {
        Assert.assertTrue(new DefaultingLookup<>(FakeLookup.using("test"), "none").lookup().isPresent());
        Assert.assertTrue(new DefaultingLookup<>(FakeLookup.using(null), "none").lookup().isPresent());
        Assert.assertTrue(new DefaultingLookup<>(FakeLookup.using(), "none").lookup().isPresent());
    }
}
