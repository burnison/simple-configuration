package ca.burnison.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;

/**
 * A source implementation intended only for single-threaded tests. This
 * class may be used as a light-weight option for unit tests.
 */
public final class TestSource implements StaticSource {
    private final Map<String, String> map = new HashMap<>();

    private TestSource() {
    }

    /**
     * Create a new, empty source with no initial values.
     *
     * @return A new source.
     */
    public static TestSource empty() {
        return new TestSource();
    }

    /**
     * Create a new source with the specified initial String value.
     * @param key The non-null key of this property.
     * @param value The value of this property.
     * @return A new source with the specified initial values.
     * @throws NullPointerException If the key is null.
     */
    public static TestSource initially(@Nonnull final String key, final String value) {
        return empty().with(key, value);
    }

    /**
     * Create a new source with the specified initial Number value.
     * @param key The non-null key of this property.
     * @param value The value of this property.
     * @return A new source with the specified initial values.
     * @throws NullPointerException If the key is null.
     */
    public static TestSource initially(@Nonnull final String key, final Number value) {
        return empty().with(key, value);
    }

    /**
     * Create a new source with the specified initial Boolean value.
     *
     * @param key The non-null key of this property.
     * @param value The value of this property.
     * @return A new source with the specified initial values.
     * @throws NullPointerException If the key is null.
     */
    public static TestSource initially(@Nonnull final String key, final Boolean value) {
        return empty().with(key, value);
    }

    /**
     * Add the specified non-null key and nullable value.
     *
     * @param key The unique property name.
     * @param value The property value.
     * @return The current source.
     * @throws NullPointerException If the key is null.
     */
    public TestSource with(@Nonnull final String key, final String value) {
        this.map.put(
            Objects.requireNonNull(key, "A property key may not be null."),
            value);
        return this;
    }

    /**
     * Add the specified non-null key and nullable value.
     *
     * @param key The unique property name.
     * @param value The property value.
     * @return The current source.
     * @throws NullPointerException If the key is null.
     */
    public TestSource with(@Nonnull final String key, final Number value) {
        return this.with(key, value == null ? null : value.toString());
    }

    /**
     * Add the specified non-null key and nullable value.
     *
     * @param key The unique property name.
     * @param value The property value.
     * @return The current source.
     * @throws NullPointerException If the key is null.
     */
    public TestSource with(@Nonnull final String key, final Boolean value) {
        return this.with(key, value == null ? null : value.toString());
    }

    /**
     * Removes a value from this source.
     *
     * @param key A non-null property key to remove.
     * @return The current source.
     */
    public TestSource without(@Nonnull final String key) {
        this.map.remove(Objects.requireNonNull(key, "A non-null key is required."));
        return this;
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
