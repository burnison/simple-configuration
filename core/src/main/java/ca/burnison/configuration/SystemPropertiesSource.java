package ca.burnison.configuration;

import java.util.Properties;

/**
 * Finds variables from the system properties. Because system properties change,
 * this class is dynamic. Because {@link Properties} is backed by a blocking
 * collection, in some instances, when performance is more critical than
 * live-reloadability, you may wish to, instead, use a {@link MapSource} with a
 * snapshot of the system properties.
 */
public final class SystemPropertiesSource implements Source {
    private static final PropertiesSource SOURCE = new PropertiesSource(System.getProperties());

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final String key) {
        return SOURCE.contains(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(final String key) {
        return SOURCE.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOrDefault(final String key, final String defaultValue) {
        return SOURCE.getOrDefault(key, defaultValue);
    }
}
