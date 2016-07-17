package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test needs to be run by Maven (or manually) to get the config.
 */
public final class EnvVarSourceTest {
    @Test
    public void get() {
        final EnvVarSource source = new EnvVarSource();
        Assert.assertEquals("33.3", source.get("envvartest.stuff"));
        Assert.assertNull(source.get("envvartest.notpresent"));
    }

    @Test
    public void getOrDefault() {
        final EnvVarSource source = new EnvVarSource();
        Assert.assertEquals("33.3", source.getOrDefault("envvartest.stuff", "99"));
        Assert.assertEquals("99", source.getOrDefault("envvartest.notpresent", "99"));
    }

    @Test
    public void contains() {
        final EnvVarSource source = new EnvVarSource();
        Assert.assertTrue(source.contains("envvartest.stuff"));
        Assert.assertFalse(source.contains("envvartest.notpresent"));
    }
}
