package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class TestSourceTest {

    @Test
    public void empty() {
        Assert.assertNull(TestSource.empty().get("c"));
    }

    @Test
    public void initially() {
        Assert.assertEquals("C", TestSource.initially("c", "C").get("c"));
    }

    @Test
    public void with() {
        final TestSource source = TestSource.initially("c", "C").with("d", "D");
        Assert.assertTrue(source.contains("c"));
        Assert.assertEquals("C", source.get("c"));

        Assert.assertTrue(source.contains("d"));
        Assert.assertEquals("D", source.get("d"));

        source.with("c", "Z");
        Assert.assertEquals("Property should have been updated.", "Z", source.get("c"));
    }

    @Test
    public void without() {
        final TestSource source = TestSource.initially("c", "C").without("c");
        Assert.assertNull(source.get("c"));
        Assert.assertFalse(source.contains("c"));
    }
}
