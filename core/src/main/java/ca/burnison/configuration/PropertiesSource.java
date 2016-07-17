package ca.burnison.configuration;

import java.util.Objects;
import java.util.Properties;
import javax.annotation.Nonnull;

/**
 * Sources properties from an arbitrary {@link Properties} object. Because the
 * backing store is a blocking collection, when performance is critical, you may
 * wish to convert the properties into a different collection type.
 */
public final class PropertiesSource implements Source {
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
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final String key) {
        return this.properties.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(final String key) {
        return Objects.toString(this.properties.get(key), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrDefault(final String key, final String defaultValue) {
        return Objects.toString(this.properties.getOrDefault(key, defaultValue), null);
    }
}
