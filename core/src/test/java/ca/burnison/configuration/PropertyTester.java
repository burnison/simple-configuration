package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public abstract class PropertyTester<T, P extends Property<T, P>> {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void get() {
        Assert.assertEquals(valueA(), create(valueA()).get());
        Assert.assertNull(create(null).get());
        Assert.assertNull(create().get());
    }

    @Test
    public void get_withFunction() {
        Assert.assertEquals(valueB(), create(valueA()).get(v -> valueB()));
        Assert.assertEquals(valueB(), create(null).get(s -> valueB()));
        Assert.assertNull(create().get(v -> valueB()));
    }

    @Test
    public void orElse() {
        Assert.assertEquals(valueA(), create(valueA()).orElse(valueB()));
        Assert.assertNull(create(null).orElse(valueB()));
        Assert.assertEquals(valueB(), create().orElse(valueB()));
    }

    @Test
    public void orElseGet() {
        Assert.assertEquals(valueA(), create(valueA()).orElseGet(() -> valueB()));
        Assert.assertEquals(valueB(), create().orElseGet(() -> valueB()));
        Assert.assertNull(create(null).orElseGet(() -> valueB()));
    }

    @Test
    public void frozen() {
        Assert.assertEquals(valueA(), create(valueA()).frozen().get());
        Assert.assertNull(create(null).frozen().get());
        Assert.assertNull(create().frozen().get());
    }

    @Test
    public void cached() {
        Assert.assertEquals(valueA(), create(valueA()).cached().get());
        Assert.assertNull(create(null).cached().get());
        Assert.assertNull(create().cached().get());
    }

    @Test
    public void mapped() {
        Assert.assertEquals(valueB(), create(valueA()).mapped(s -> valueB()).get());
        Assert.assertEquals(valueB(), create(null).mapped(s -> valueB()).get());
        Assert.assertNull(create().mapped(s -> valueB()).get());
    }

    @Test
    public void validate() {
        Assert.assertEquals(valueA(), create(valueA()).validated(a -> true).get());
        Assert.assertNull(create(null).validated(s -> true).get());
        Assert.assertNull(create().validated(s -> true).get());
        Assert.assertNull(create().validated(s -> false).get());
    }

    @Test
    public void validate_invalid() {
        final P p = create(valueA()).validated(s -> false);
        thrown.expect(PropertyInvalidException.class);
        p.get();
    }

    @Test
    public void tested() {
        Assert.assertEquals(valueA(), create(valueA()).validated(a -> true).tested().get());
        Assert.assertNull(create(null).validated(s -> true).tested().get());
        Assert.assertNull(create().validated(s -> true).tested().get());
        Assert.assertNull(create().validated(s -> false).tested().get());
    }

    @Test(expected = PropertyInvalidException.class)
    public void tested_invalid() {
        create(valueA()).validated(s -> false).tested();
    }

    @Test
    public void required() {
        Assert.assertEquals(valueA(), create(valueA()).required().get());
        Assert.assertNull(create(null).required().get());
    }

    @Test
    public void required_absent() {
        thrown.expect(PropertyMissingException.class);
        Assert.assertNull(create().required().get());
    }

    @Test
    public void required_withDefault() {
        Assert.assertEquals(valueA(), create(valueA()).required(valueB()).get());
        Assert.assertNull(create(null).required(valueB()).get());
        Assert.assertEquals(valueB(), create().required(valueB()).get());
    }

    @Test
    public void coalesced() {
        Assert.assertEquals(valueA(), create(valueA()).coalesced(valueB()).get());
        Assert.assertEquals(valueB(), create(null).coalesced(valueB()).get());
        Assert.assertNull(create().coalesced(valueB()).get());
    }

    @Test
    public void unwrapped() {
        final Object unmanaged = new Object();
        Assert.assertSame(unmanaged, create(valueA()).unwrapped(x -> unmanaged).get());
        Assert.assertSame(unmanaged, create(null).unwrapped(x -> unmanaged).get());
        Assert.assertNull(create().unwrapped(x -> unmanaged).get());
    }

    @Test
    public void aBitofEverything_nonsenseTest() {
        final Object unmanaged = new Object();
        final String expected = unmanaged.toString();
        final Property<?, ?> p = create(valueA())
                .required()
                .mapped(x -> valueB())
                .coalesced(valueA())
                .unwrapped(s -> unmanaged)
                .validated(o -> o != null)
                .unwrapped(Object::toString)
                .validated(s -> s.length() == expected.length())
                .mapped(s -> s.toString())
                .frozen();
        Assert.assertEquals(expected, p.get());
    }

    protected abstract T valueA();

    protected abstract T valueB();

    protected abstract P create(final T value);

    protected abstract P create();
}
