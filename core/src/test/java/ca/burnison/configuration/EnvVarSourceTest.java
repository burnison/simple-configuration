package ca.burnison.configuration;

/**
 * This test needs to be run by Maven (or manually) to get the config.
 */
public final class EnvVarSourceTest extends SourceTester {
    @Override
    protected Source getSource() {
        return new EnvVarSource();
    }

    @Override
    protected Source getSource(final KeyTransformer keyx) {
        return new EnvVarSource(keyx);
    }

    @Override
    protected String keyPresent() {
        return "envvartest.stuff";
    }

    @Override
    protected String valuePresent() {
        return "33.3";
    }

    @Override
    protected String keyAbsent() {
        return "envvartest.missing";
    }
}
