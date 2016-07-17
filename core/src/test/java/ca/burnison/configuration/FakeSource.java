package ca.burnison.configuration;

import java.util.HashMap;
import java.util.Map;

final class FakeSource implements StaticSource {
    private final Map<String, String> map = new HashMap<>();

    private FakeSource() {
    }

    static FakeSource create() {
        return new FakeSource();
    }

    FakeSource with(final String key, final Object value) {
        this.map.put(key, value.toString());
        return this;
    }

    @Override
    public boolean contains(final String key) {
        return this.map.containsKey(key);
    }

    @Override
    public String get(final String key) {
        return this.map.get(key);
    }

    @Override
    public String getOrDefault(final String key, final String defaultValue) {
        return this.map.getOrDefault(key, defaultValue);
    }
}
