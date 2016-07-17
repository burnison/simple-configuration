package ca.burnison.configuration;

import org.junit.Test;

public final class ValidatingLookupTest {

    @Test
    public void lookup() {
        new ValidatingLookup<>(FakeLookup.using(3), t -> t.equals(3)).lookup();
    }

    @Test(expected = PropertyInvalidException.class)
    public void lookup_notValid_exception() {
        new ValidatingLookup<>(FakeLookup.using(3), t -> t.equals(5)).lookup();
    }
}
