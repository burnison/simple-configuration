package ca.burnison.configuration;

public final class CharacterProperty extends Property<Character, CharacterProperty> {

    CharacterProperty(final String name, final Lookup<Character> lookup) {
        super(name, lookup);
    }

    public int charValue() {
        return this.get().charValue();
    }
}
