package ca.burnison.configuration;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

/**
 * A map source is a static, immutable source backed by a map-type. During
 * construction, the inbound map is cloned. As a result, subsequent changes to
 * the provided map will not be observed.
 */
public final class MapSource extends BaseSource implements StaticSource {
    private final Map<String, String> map;

    /**
     * Create a new instance with a clone of the specified map.
     *
     * @param map The backing map to clone.
     * @throws NullPointerException If the map is null.
     */
    public MapSource(@Nonnull final Map<String, String> map) {
        this.map = clone(map);
    }

    /**
     * Create a new instance with a clone of the specified map and key
     * transformer.
     *
     * @param map The backing map to clone.
     * @param keyTransformer A transformer to apply to keys prior to lookup.
     * @throws NullPointerException If either property is null.
     */
    public MapSource(@Nonnull final Map<String, String> map, @Nonnull final KeyTransformer keyTransformer) {
        super(keyTransformer);
        this.map = clone(map);
    }

    @Override
    protected boolean containsMapped(final String key) {
        return this.map.containsKey(key);
    }

    @Override
    protected String getMapped(final String key) {
        return this.map.get(key);
    }

    @Override
    protected String getOrDefaultMapped(final String key, final String defaultValue) {
        return this.map.getOrDefault(key, defaultValue);
    }

    private static Map<String, String> clone(final Map<String, String> map) {
        return Objects.requireNonNull(map, "A non-null map is required.")
            .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
