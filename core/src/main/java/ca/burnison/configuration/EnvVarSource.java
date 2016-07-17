package ca.burnison.configuration;

/**
 * Finds properties sourced from environment variables. Because environmental
 * variables are determined at JVM start-up time, this source is static and
 * will not reload.
 */
public final class EnvVarSource implements StaticSource {
    private static final MapSource SOURCE = new MapSource(System.getenv());

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
