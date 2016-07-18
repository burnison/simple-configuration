package ca.burnison.configuration;

import javax.annotation.Nonnull;

/**
 * Finds properties sourced from environment variables. Because environmental
 * variables are determined at JVM start-up time, this source is static and
 * will not reload.
 */
public final class EnvVarSource extends BaseSource implements StaticSource {
    private static final MapSource SOURCE = new MapSource(System.getenv());

    /**
     * Create a new instance.
     */
    public EnvVarSource() {
    }

    /**
     * Create a new instance with the specified key transformer.
     *
     * @param keyTransformer A transformer to apply to keys prior to lookup.
     * @throws NullPointerException If any keyTransformer is null.
     */
    public EnvVarSource(@Nonnull final KeyTransformer keyTransformer) {
        super(keyTransformer);
    }

    @Override
    protected boolean containsMapped(final String key) {
        return SOURCE.contains(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getMapped(final String key) {
        return SOURCE.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getOrDefaultMapped(final String key, final String defaultValue) {
        return SOURCE.getOrDefault(key, defaultValue);
    }
}
