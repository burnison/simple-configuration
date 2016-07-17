package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class CachingLookupTest {
    @Test
    public void lookup() {
        Assert.assertEquals("test", of("test").lookup().get());
        Assert.assertEquals(5L, of(5L).lookup().get().longValue());
        Assert.assertNull(of(null).lookup().get());
        Assert.assertNull(new CachingLookup<>(FakeLookup.using()).lookup().get());
    }

    @Test
    public void present() {
        Assert.assertTrue("test", of("test").lookup().isPresent());
        Assert.assertTrue(of(null).lookup().isPresent());
        Assert.assertFalse(new CachingLookup<>(FakeLookup.using()).lookup().isPresent());
    }

    @Test
    public void lookup_bustsCache() {
        final FakeLookup<String> source = FakeLookup.using("test");
        final CachingLookup<String> cached = new CachingLookup<>(source);

        final String value = cached.lookup().get();
        Assert.assertSame(value, cached.lookup().get());

        final String value2 = "foobar";
        source.update(value2);
        cached.changed();

        Assert.assertNotSame(value, cached.lookup().get());
        Assert.assertSame(value2, cached.lookup().get());
    }

    private static <T> CachingLookup<T> of(final T t) {
        return new CachingLookup<>(FakeLookup.using(t));
    }
}
