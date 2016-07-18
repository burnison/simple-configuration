package ca.burnison.configuration;

import java.util.Objects;
import java.util.Properties;
import javax.annotation.Nonnull;

/**
 * Sources properties from an arbitrary {@link Properties} object. Because the
 * backing store is a blocking collection, when performance is critical, you may
 * wish to convert the properties into a different collection type.
 */
public final class PropertiesSource extends BaseSource implements Source {
    private final Properties properties;

    /**
     * Create a new instance with the specified properties.
     *
     * @param properties The backing properties to use.
     * @throws NullPointerException If properties is null.
     */
    public PropertiesSource(@Nonnull final Properties properties) {
        this.properties = Objects.requireNonNull(properties, "A non-null properties object must be provided.");
    }

    /**
     * Create a new istance with the specified properties and key transformer.
     *
     * @param properties The backing properties to use.
     * @param keyTransformer A transformer to apply to keys prior to lookup.
     * @throws NullPointerException If any argument is null.
     */
    public PropertiesSource(@Nonnull final Properties properties, @Nonnull final KeyTransformer keyTransformer) {
        super(keyTransformer);
        this.properties = Objects.requireNonNull(properties, "A non-null properties object must be provided.");
    }

    @Override
    protected boolean containsMapped(final String key) {
        return this.properties.containsKey(key);
    }

    @Override
    protected String getMapped(final String key) {
        return Objects.toString(this.properties.get(key), null);
    }

    @Override
    protected String getOrDefaultMapped(final String key, final String defaultValue) {
        return Objects.toString(this.properties.getOrDefault(key, defaultValue), null);
    }
}
