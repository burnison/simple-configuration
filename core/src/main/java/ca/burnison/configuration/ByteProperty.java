package ca.burnison.configuration;

public final class ByteProperty extends Property<Byte, ByteProperty> {

    ByteProperty(final String name, final Lookup<Byte> lookup) {
        super(name, lookup);
    }

    public int byteValue() {
        return this.get().byteValue();
    }
}
