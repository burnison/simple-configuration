package ca.burnison.configuration;

import java.math.BigDecimal;

public final class BigDecimalProperty extends Property<BigDecimal, BigDecimalProperty> {

    BigDecimalProperty(final String name, final Lookup<BigDecimal> lookup) {
        super(name, lookup);
    }
}
