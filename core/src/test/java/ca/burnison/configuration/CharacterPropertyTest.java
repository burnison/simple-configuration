package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class CharacterPropertyTest extends PropertyTester<Character, CharacterProperty> {

    @Test
    public void charValue() {
        Assert.assertEquals('a', create('a').charValue());
    }

    @Override
    protected Character valueA() {
        return 'a';
    }

    @Override
    protected Character valueB() {
        return 'b';
    }

    @Override
    protected CharacterProperty create(final Character value) {
        return new CharacterProperty("test", FakeLookup.using(value));
    }

    @Override
    protected CharacterProperty create() {
        return new CharacterProperty("test", FakeLookup.using());
    }
}
