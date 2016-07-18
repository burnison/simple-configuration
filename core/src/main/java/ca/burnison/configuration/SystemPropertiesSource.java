package ca.burnison.configuration;

import java.util.Properties;
import javax.annotation.Nonnull;

/**
 * Finds variables from the system properties. Because system properties change,
 * this class is dynamic. Because {@link Properties} is backed by a blocking
 * collection, in some instances, when performance is more critical than
 * live-reloadability, you may wish to, instead, use a {@link MapSource} with a
 * snapshot of the system properties.
 */
public final class SystemPropertiesSource extends BaseSource implements Source {
    private static final PropertiesSource SOURCE = new PropertiesSource(System.getProperties());

    public SystemPropertiesSource() {
    }

    /**
     * Create a new instance using the specified key transformer.
     *
     * @param keyTransformer A transformer to apply to keys prior to lookup.
     * @throws NullPointerException If keyTransformer is null.
     */
    public SystemPropertiesSource(@Nonnull final KeyTransformer keyTransformer) {
        super(keyTransformer);
    }

    @Override
    protected boolean containsMapped(final String key) {
        return SOURCE.contains(key);
    }

    @Override
    protected String getMapped(final String key) {
        return SOURCE.get(key);
    }

    @Override
    protected String getOrDefaultMapped(final String key, final String defaultValue) {
        return SOURCE.getOrDefault(key, defaultValue);
    }
}
