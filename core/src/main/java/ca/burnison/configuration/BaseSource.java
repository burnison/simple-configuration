package ca.burnison.configuration;

import java.util.Objects;
import javax.annotation.Nonnull;

/**
 * A base class for most types of sources.
 */
public abstract class BaseSource implements Source {
    private final KeyTransformer keyTransformer;

    /**
     * Create a new base source with no key transformer.
     */
    protected BaseSource() {
        this.keyTransformer = null;
    }

    /**
     * Create a new base source using the specified key transformer.
     *
     * @param keyTransformer The non-null key transformer to apply.
     * @throws NullPointerException If keyTransformer is null.
     */
    protected BaseSource(@Nonnull final KeyTransformer keyTransformer) {
        this.keyTransformer = Objects.requireNonNull(keyTransformer, "A non-null key transformer is required.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean contains(final String key) {
        return this.containsMapped(this.mapKey(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String get(final String key) {
        return this.getMapped(this.mapKey(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getOrDefault(final String key, final String defaultValue) {
        return this.getOrDefaultMapped(this.mapKey(key), defaultValue);
    }

    /**
     * Given the mapped key, determine if the property exists.
     *
     * @param key The mapped key.
     * @return true or false.
     */
    protected abstract boolean containsMapped(final String key);

    /**
     * Given the mapped key, find the property.
     *
     * @param key The mapped key.
     * @return The property or null.
     */
    protected abstract String getMapped(final String key);

    /**
     * Given the mapped key, find the property or return the default value.
     *
     * @param key The mapped key.
     * @param defaultValue The substitute value.
     * @return The property or the default value.
     */
    protected abstract String getOrDefaultMapped(final String key, final String defaultValue);

    private String mapKey(final String key) {
        if (this.keyTransformer == null) {
            return key;
        }
        return this.keyTransformer.apply(key);
    }
}
