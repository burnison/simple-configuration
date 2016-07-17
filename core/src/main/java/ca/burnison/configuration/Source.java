package ca.burnison.configuration;

import javax.annotation.Nonnull;

public interface Source {
    /**
     * Determines if the specified key is present in this repository.
     *
     * @param key The property name.
     * @return true or false.
     */
    boolean contains(@Nonnull final String key);

    /**
     * Get the specified key from this registry or null if not present.
     *
     * @param key The property name.
     * @return The current value.
     */
    String get(@Nonnull final String key);

    /**
     * Gets the specified key from this registry or returns the defaut value
     * if not present. This operation should be atomic whenever possible. If
     * an atomic operation is not possible, consider falling-back to a
     * check-get-check operation if the underlying data structure is not
     * static.
     *
     * @param key The property name.
     * @param defaultValue A default falue of the property is not present.
     * @return The current property value or the specified default value.
     */
    String getOrDefault(@Nonnull final String key, final String defaultValue);
//
//    /**
//     * Subscribe a lookup to this source's update mechanism.
//     *
//     * @param lookup The lookup to subscribe.
//     */
//    void subscribe(@Nonnull final Lookup<?> lookup);
//
//    /**
//     * Unsubscribe a lookup from this source's update mechanism.
//     *
//     * @param lookup The lookup to unsubscribe.
//     */
//    void unsubscribe(@Nonnull final Lookup<?> lookup);
//
//    /**
//     * Notify all of the matching subscribed lookups of a change.
//     *
//     * @param name The name of each affected lookup.
//     */
//    void change(@Nonnull final String name);
}
