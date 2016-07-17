package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test needs to be run by Maven (or manually) to get the config.
 */
public final class SystemPropertiesSourceTest {
    @Test
    public void get() {
        final SystemPropertiesSource source = new SystemPropertiesSource();
        Assert.assertEquals("44.4", source.get("proptest.stuff"));
        Assert.assertNull(source.get("proptest.notpresent"));
    }

    @Test
    public void getOrDefault() {
        final SystemPropertiesSource source = new SystemPropertiesSource();
        Assert.assertEquals("44.4", source.getOrDefault("proptest.stuff", "99"));
        Assert.assertEquals("99", source.getOrDefault("proptest.notpresent", "99"));
    }

    @Test
    public void contains() {
        final SystemPropertiesSource source = new SystemPropertiesSource();
        Assert.assertTrue(source.contains("proptest.stuff"));
        Assert.assertFalse(source.contains("proptest.notpresent"));
    }
}
