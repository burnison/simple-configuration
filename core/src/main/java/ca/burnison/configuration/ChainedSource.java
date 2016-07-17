package ca.burnison.configuration;

import java.util.Objects;

public final class ChainedSource implements Source {
    @SuppressWarnings("findbugs:DM_STRING_VOID_CTOR")
    private static final String MISSING = new String();

    private final Source[] chain;

    private ChainedSource(final Source primary, final Source secondary, final Source ... alternate) {
        this.chain = new Source[Objects.requireNonNull(alternate).length + 2];
        this.chain[0] = Objects.requireNonNull(primary);
        this.chain[1] = Objects.requireNonNull(secondary);
        System.arraycopy(alternate, 0, chain, 2, alternate.length);
    }

    public static ChainedSource linking(final Source primary, final Source secondary) {
        return linking(primary, secondary, new Source[0]);
    }

    public static ChainedSource linking(final Source primary, final Source secondary, final Source ... alternate) {
        return new ChainedSource(primary, secondary, alternate);
    }

    @Override
    public boolean contains(final String key) {
        for (final Source r : this.chain) {
            if (r.contains(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String get(final String key) {
        for (final Source r : this.chain) {
            final String value = r.getOrDefault(key, MISSING);
            if (value != MISSING) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String getOrDefault(final String key, final String defaultValue) {
        final String value = this.get(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }
}
