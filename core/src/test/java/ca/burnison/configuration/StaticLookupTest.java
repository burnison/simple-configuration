package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class StaticLookupTest {

    @Test
    public void lookup() {
        Assert.assertEquals("test", of("test").lookup().get());
        Assert.assertEquals(5L, of(5L).lookup().get().longValue());
        Assert.assertNull(of(null).lookup().get());
    }

    @Test
    public void present() {
        Assert.assertTrue("test", of("test").lookup().isPresent());
        Assert.assertTrue(of(null).lookup().isPresent());
    }

    private static <T> StaticLookup<T> of(final T t) {
        return new StaticLookup<>(FakeLookup.using(t));
    }
}
