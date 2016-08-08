package ca.burnison.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.Assert;
import org.junit.Test;

public final class ConcurrentMapSourceTest extends SourceTester {

    private final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
    {
        map.put("map.prop", "33.333333");
    }

    @Test
    public void new_immutableCopy() {
        final Map<String, String> map = new HashMap<>();
        final MapSource source = new MapSource(map);
        map.put("c", "c");
        Assert.assertNull(source.get("c"));
    }

    @Override
    protected Source getSource() {
        return new MapSource(map);
    }

    @Override
    protected Source getSource(final KeyTransformer keyx) {
        return new MapSource(map, keyx);
    }

    @Override
    protected String keyPresent() {
        return "map.prop";
    }

    @Override
    protected String valuePresent() {
        return "33.333333";
    }

    @Override
    protected String keyAbsent() {
        return "map.prop.missing";
    }
}
