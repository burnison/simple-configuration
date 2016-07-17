package ca.burnison.configuration;

import java.util.Properties;
import org.junit.Assert;
import org.junit.Test;

public final class PropertiesSourceTest {
    private final Properties properties = new Properties();
    {
        properties.setProperty("test", "yup");
    }

    @Test
    public void get() {
        final PropertiesSource source = new PropertiesSource(properties);
        Assert.assertEquals("yup", source.get("test"));
        Assert.assertNull(source.get("missing"));
    }

    @Test
    public void getOrDefault() {
        final PropertiesSource source = new PropertiesSource(properties);
        Assert.assertEquals("yup", source.getOrDefault("test", "99"));
        Assert.assertEquals("99", source.getOrDefault("missing", "99"));
    }

    @Test
    public void contains() {
        final PropertiesSource source = new PropertiesSource(properties);
        Assert.assertTrue(source.contains("test"));
        Assert.assertFalse(source.contains("missing"));
    }
}
