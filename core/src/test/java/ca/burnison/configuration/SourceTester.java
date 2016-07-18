package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public abstract class SourceTester {
    private static final KeyTransformer KEY_TRANSFORMER = s -> s.toLowerCase().replace('_', '.');

    @Test
    public void get() {
        Assert.assertEquals(valuePresent(), getSource().get(keyPresent()));
        Assert.assertNull(getSource().get(keyAbsent()));
    }

    @Test
    public void getOrDefault() {
        Assert.assertEquals(valuePresent(), getSource().getOrDefault(keyPresent(), "99"));
        Assert.assertEquals("99", getSource().getOrDefault(keyAbsent(), "99"));
    }

    @Test
    public void contains() {
        Assert.assertTrue(getSource().contains(keyPresent()));
        Assert.assertFalse(getSource().contains(keyAbsent()));
    }

    @Test
    public void withKeyTransformer() {
        final String key = keyPresent().toUpperCase().replace('.', '_');
        Assert.assertEquals(valuePresent(), getSource(KEY_TRANSFORMER).get(key));
        Assert.assertEquals(valuePresent(), getSource(KEY_TRANSFORMER).getOrDefault(key, "99"));
        Assert.assertTrue(getSource(KEY_TRANSFORMER).contains(key));
    }

    protected abstract Source getSource(final KeyTransformer keyx);

    protected abstract Source getSource();

    protected abstract String keyPresent();

    protected abstract String valuePresent();

    protected abstract String keyAbsent();
}
