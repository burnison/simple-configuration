package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class RequiringLookupTest {

    @Test
    public void lookup() {
        Assert.assertEquals("test", new RequiringLookup<>(FakeLookup.using("test")).lookup().get());
        Assert.assertNull(new RequiringLookup<>(FakeLookup.using(null)).lookup().get());
    }

    @Test(expected = PropertyMissingException.class)
    public void lookup_fail() {
        new RequiringLookup<>(FakeLookup.using()).lookup();
    }

    @Test
    public void present() {
        Assert.assertTrue(new RequiringLookup<>(FakeLookup.using("test")).lookup().isPresent());
        Assert.assertTrue(new RequiringLookup<>(FakeLookup.using(null)).lookup().isPresent());
    }
}
