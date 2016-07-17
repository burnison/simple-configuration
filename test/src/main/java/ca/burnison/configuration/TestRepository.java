package ca.burnison.configuration;

/**
 * A repository that may be used for testing.
 */
public final class TestRepository extends Repository {
    private final TestSource source;

    private TestRepository(final TestSource source) {
        super(source);
        this.source = source;
    }

    /**
     * Create a new instance with an empty test source.
     *
     * @return A new empty test repository.
     */
    public static TestRepository empty() {
        return new TestRepository(TestSource.empty());
    }

    /**
     * Create a new instance with a default test source. The specified
     * parameters will be added as the first property.
     *
     * @param name The first property name to add.
     * @param value The first property value to add.
     * @return A new test repository with the specified initial value.
     */
    public static TestRepository initially(final String name, final String value) {
        return new TestRepository(TestSource.initially(name, value));
    }

    /**
     * Create a new instance using the specified source.
     *
     * @param source The source this repository will use.
     * @return A new test repository using the specified source.
     */
    public static TestRepository using(final TestSource source) {
        return new TestRepository(source);
    }

    /**
     * Add a property to the underlying source.
     *
     * @param name The property to add.
     * @param value The property value to add.
     * @return This instance.
     */
    public TestRepository with(final String name, final String value) {
        this.source.with(name, value);
        return this;
    }

    /**
     * Add a property to the underlying source.
     *
     * @param name The property to add.
     * @param value The property value to add.
     * @return This instance.
     */
    public TestRepository with(final String name, final Number value) {
        this.source.with(name, value);
        return this;
    }

    /**
     * Add a property to the underlying source.
     *
     * @param name The property to add.
     * @param value The property value to add.
     * @return This instance.
     */
    public TestRepository with(final String name, final Boolean value) {
        this.source.with(name, value);
        return this;
    }

    /**
     * Remove a property from the underlying source.
     *
     * @param name The property name to remove.
     * @return This instance.
     */
    public TestRepository without(final String name) {
        this.source.without(name);
        return this;
    }
}
