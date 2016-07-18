package ca.burnison.configuration;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public final class MapSourceTest extends SourceTester {

    private final Map<String, String> map = new HashMap<>();
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
