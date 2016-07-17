package ca.burnison.configuration;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A map source is a static, immutable source backed by a map-type. During
 * construction, the inbound map is cloned. As a result, subsequent changes to
 * the provided map will not be observed.
 */
public final class MapSource implements StaticSource {
    private final Map<String, String> map;

    /**
     * Create a new instance with a clone of the specified map.
     *
     * @param map The backing map to clone.
     * @throws NullPointerException If the map is null.
     */
    public MapSource(final Map<String, String> map) {
        this.map = Objects.requireNonNull(map, "A non-null map is required.")
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final String key) {
        return this.map.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(final String key) {
        return this.map.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrDefault(final String key, final String defaultValue) {
        return this.map.getOrDefault(key, defaultValue);
    }
}
