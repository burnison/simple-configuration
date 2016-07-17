# simple-config

A simple, dependency-free, fluid, high-performance configuration framework for
JVM-based languages.

```xml
<dependency>
    <groupId>ca.burnison.configuration</groupId>
    <artifactId>config-core</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

<!-- For use of TestRepository in unit tests. -->
<dependency>
    <groupId>ca.burnison.configuration</groupId>
    <artifactId>config-test</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

# Usage

To use `simple-config`, you need only think about 3 concepts:

*   A `Source` is an extensible provider of properties.
*   A `Property` is a thread-safe proxy to a source and provides property
    values.
*   A `Repository` is the primary interface to access properties.

To quickly get up-and-running, you can use the built-in sources, such as
`EnvVarSource` and `SystemPropertiesSource`, which give you access, in declared
order, to the environmental variables and system properties.

```java
final Source source = ChainedSource.linking(
    new SystemPropertiesSource(),
    new EnvVarSource()
);
final Repository repo = new Repository(source);
```

Once a repository is set-up, usually as a singleton, you can begin getting
property instances. Properties are thread-safe and are intended to be declared
once, usually during singleton construction (for example, in controllers or
data-access objects).

```java
public final class MaintenanceModeFilter {
    private final BooleanProperty inMaintenanceMode;

    public MaintenanceModeFilter(final Repository repo) {
        this.inMaintenanceMode = repo.aBoolean("maintenance.mode");
    }

    @Override
    public void doFilter(...) {
        if (this.inMaintenanceMode.booleanValue()) {
            response.sendError(SC_SERVICE_UNAVAILABLE, "In maintenance mode.");
        } else {
            chain.doFilter(request, response);
        }
    }
}
```


# Property types

Several common property types are available, such as

* `BooleanProperty`
* `ByteProperty`
* `ShortProperty`
* `IntegerProperty`
* `LongProperty`
* `BigDecimalProperty`
* `CharacterProperty`
* `StringProperty`

Each type presents a slightly more friendly API for accessing the underlying
values. For example, `BooleanProperty::booleanValue` will unbox the underlying
Boolean object.

While these default property types are most frequently all you will need, the
API also offers an `AnyTypeProperty` that may be used for non-default types.



# Property Methods

The `Property` types offer several powerful functional methods. These methods
may be used to validate the integrity of a property and perform other types
of transformations:

*   `mapped` - Applies a functional mapping to the property. For example, a
    String property can be upper-cased: `aString.mapped(String::toUpperCase)`.

*   `validated` - Ensures that the value of a property matches some predicate.
    For example, you can ensure that a hex-encoded key is an even length before
    decoding it: `aString.validated(s -> s.length() & 1 == 0)`.

*   `required` - Ensures that a property is present in some repository. This
    method also takes a value that may be used as a default value if the
    property is not present in any source: `anInt.required(5)`.

*   `coalesced` - Swaps a *present*, `null` property with a default value.

*   `frozen` - Snapshots the current property value and continually replays it.

*   `cached` - Snapshots the current property value and continually replays it
    until the underlying source changes it.

*   `unwrapped` - Applies a function to the property and returns it as an
    `AnyTypeProperty`. Once unwrapped, a property cannot be wrapped again.

These methods should be chained together and permanently retained.


# Example: Simple Validation & Transformation

For example, the following pipeline will validate a property, transform it, and
then cache the result until the next invalidation:

```java
private final StringProperty title = repo.aString("my.page.title")
    .required("page title missing")
    .coalesced("null page title")
    .validated(s -> s.length() > 0)
    .mapped(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
    .cached();
```

All subsequent calls to `title.get()` will return a capitalized value for the
page and will only be recomputed if the underlying property, `my.page.title`
is changed in some source.


# Example: Unwrapping & Caching

In some instances, you may wish to use a property to deserialize some specific
value, such as a cipher key and refrain from having to recompute it unless of an
underlying change. In the following example, a hex-encoded AES session key is
transformed into a secret key.

```java
public final class Encrypter {
    private final AnyTypeProperty<? extends SecretKey> key;

    public Encrypter(final Repository repo) {
        this.key = repo.aString("secret.key")
            .required()
            .unwrapped(Hex::decodeString)
            .unwrapped(b -> new SecretKeySpec(b, "AES"))
            .cached();
    }

    public byte[] encrypt(final String value) throws Exception {
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(ENCRYPT_MODE, this.key.get());
        return cipher.doFinal(value.getBytes());
    }
}
```

To reduce the cost of continually decoding and instantiating a new secret key,
the `cached` method is called, which caches the transformed result.



# Example: Testing

For simplified testing, the `config-test` module offers a simple `TestSource`
class. This source may be used liberally withouth having to mock, stub, or have
a complex test set-up.

```java
final Repository repo = TestRepository.initially("first.property", "something")
    .with("second.property", "another thing")
    .with("third.property", "a third thing");
```

The `TestRepository.empty()` and `TestRepository.using(TestSource)` factory
methods may also be used directly. The test repository may be changed on the fly
within a single test, which may help testing properties that change:

```java
final StringProperty p = repo.aString("second.property");
Assert.assertEquals("another thing", p.get());

repo.without("second.property");
Assert.assertNull(p.get());
```

