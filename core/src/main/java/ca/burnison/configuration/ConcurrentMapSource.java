package ca.burnison.configuration;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nonnull;

/**
 * A volatile map source backed by a {@link ConcurrentMap}. Changes to the
 * underlying map will be immediately visible to all properties; however, all
 * key and value references themselves must be thread safe.
 */
public final class ConcurrentMapSource extends BaseSource {
    private final ConcurrentMap<String, String> map;

    /**
     * Create a new instance with the specified map.
     *
     * @param map The backing map to clone.
     * @throws NullPointerException If the map is null.
     */
    public ConcurrentMapSource(@Nonnull final ConcurrentMap<String, String> map) {
        this.map = Objects.requireNonNull(map);
    }

    /**
     * Create a new instance with a clone of the specified map and key
     * transformer.
     *
     * @param map The backing map to clone.
     * @param keyTransformer A transformer to apply to keys prior to lookup.
     * @throws NullPointerException If either property is null.
     */
    public ConcurrentMapSource(
        @Nonnull final ConcurrentMap<String, String> map,
        @Nonnull final KeyTransformer keyTransformer
    ) {
        super(keyTransformer);
        this.map = Objects.requireNonNull(map);
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
}
