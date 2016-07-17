package ca.burnison.configuration;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

public class Repository {
    @SuppressWarnings("findbugs:DM_STRING_VOID_CTOR")
    private static final String MISSING = new String();

    public final Source source;

    public Repository(final Source source) {
        this.source = Objects.requireNonNull(source);
    }

    public StringProperty aString(final String key) {
        return new StringProperty(key, new SourceLookup(key));
    }

    public CharacterProperty aCharacter(final String key) {
        return new CharacterProperty(key, lookup(key, s -> s == null ? null : s.charAt(0)));
    }

    public ByteProperty aByte(final String key) {
        return new ByteProperty(key, lookup(key, s -> s == null ? null : Byte.valueOf(s)));
    }

    public ShortProperty aShort(final String key) {
        return new ShortProperty(key, lookup(key, s -> s == null ? null : Short.valueOf(s)));
    }

    public IntegerProperty anInteger(final String key) {
        return new IntegerProperty(key, lookup(key, s -> s == null ? null : Integer.valueOf(s)));
    }

    public LongProperty aLong(final String key) {
        return new LongProperty(key, lookup(key, s -> s == null ? null : Long.valueOf(s)));
    }

    public BooleanProperty aBoolean(final String key) {
        return new BooleanProperty(key, lookup(key, s -> s == null ? null : Boolean.valueOf(s)));
    }

    public BigDecimalProperty aBigDecimal(final String key) {
        return new BigDecimalProperty(key, lookup(key, s -> s == null ? null : new BigDecimal(s)));
    }

    private <T> Lookup<T> lookup(final String key, final Function<String, T> fn) {
        return new TransformingLookup<>(new SourceLookup(key), fn);
    }

    private final class SourceLookup implements Lookup<String> {
        private final String key;

        private SourceLookup(final String key) {
            this.key = key;
        }

        @Override
        public String name() {
            return this.key;
        }

        @Override
        public Result<String> lookup() {
            final String value = Repository.this.source.getOrDefault(this.key, MISSING);
            if (value == Repository.MISSING) {
                return Result.with();
            }
            return Result.with(value);
        }

        @Override
        public void changed() {
        }
    }
}
