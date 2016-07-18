package ca.burnison.configuration;

/**
 * This test needs to be run by Maven (or manually) to get the config.
 */
public final class SystemPropertiesSourceTest extends SourceTester {

    @Override
    protected Source getSource() {
        return new SystemPropertiesSource();
    }

    @Override
    protected Source getSource(final KeyTransformer keyx) {
        return new SystemPropertiesSource(keyx);
    }

    @Override
    protected String keyPresent() {
        return "proptest.stuff";
    }

    @Override
    protected String valuePresent() {
        return "44.4";
    }

    @Override
    protected String keyAbsent() {
        return "proptest.missing";
    }
}
