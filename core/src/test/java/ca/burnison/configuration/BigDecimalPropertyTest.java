package ca.burnison.configuration;

import java.math.BigDecimal;

public final class BigDecimalPropertyTest extends PropertyTester<BigDecimal, BigDecimalProperty> {
    @Override
    protected BigDecimal valueA() {
        return BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal valueB() {
        return BigDecimal.ONE;
    }

    @Override
    protected BigDecimalProperty create(final BigDecimal value) {
        return new BigDecimalProperty("test", FakeLookup.using(value));
    }

    @Override
    protected BigDecimalProperty create() {
        return new BigDecimalProperty("test", FakeLookup.using());
    }
}
