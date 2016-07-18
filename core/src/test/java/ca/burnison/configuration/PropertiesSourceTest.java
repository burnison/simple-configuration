package ca.burnison.configuration;

import java.util.Properties;

public final class PropertiesSourceTest extends SourceTester {
    private final Properties properties = new Properties();

    {
        properties.setProperty("test.property", "yup");
    }

    @Override
    protected Source getSource() {
        return new PropertiesSource(properties);
    }

    @Override
    protected Source getSource(final KeyTransformer keyx) {
        return new PropertiesSource(properties, keyx);
    }

    @Override
    protected String keyPresent() {
        return "test.property";
    }

    @Override
    protected String valuePresent() {
        return "yup";
    }

    @Override
    protected String keyAbsent() {
        return "test.missing";
    }
}
