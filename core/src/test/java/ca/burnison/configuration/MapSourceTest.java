package ca.burnison.configuration;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public final class MapSourceTest {

    @Test
    public void new_immutableCopy() {
        final Map<String, String> map = new HashMap<>();
        final MapSource source = new MapSource(map);
        map.put("c", "c");
        Assert.assertNull(source.get("c"));
    }

    @Test
    public void get() {
        final Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");

        final MapSource source = new MapSource(map);
        Assert.assertEquals("A", source.get("a"));
        Assert.assertEquals("B", source.get("b"));
        Assert.assertNull(source.get("c"));
    }

    @Test
    public void getOrDefault() {
        final Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");

        final MapSource source = new MapSource(map);
        Assert.assertEquals("A", source.getOrDefault("a", "missing"));
        Assert.assertEquals("B", source.getOrDefault("b", "missing"));
        Assert.assertEquals("missing", source.getOrDefault("c", "missing"));
    }

    @Test
    public void contains() {
        final Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");

        final MapSource source = new MapSource(map);
        Assert.assertTrue("A", source.contains("a"));
        Assert.assertTrue("B", source.contains("b"));
        Assert.assertFalse(source.contains("c"));
    }
}
