package ca.burnison.configuration;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public final class RepositoryTest {
    private static final Repository REPO = new Repository(FakeSource.create()
        .with("a.string", "yup")
        .with("a.char", "y")
        .with("a.byte", 3)
        .with("a.short", 4)
        .with("a.int", 5)
        .with("a.long", 6)
        .with("a.bigdec", "33.33"));

    @Test
    public void aString() {
        Assert.assertEquals("yup", REPO.aString("a.string").get());
    }

    @Test
    public void aCharacter() {
        Assert.assertEquals('y', REPO.aCharacter("a.char").charValue());
    }

    @Test
    public void aByte() {
        Assert.assertEquals(3, REPO.aByte("a.byte").byteValue());
    }

    @Test
    public void aShort() {
        Assert.assertEquals(4, REPO.aShort("a.short").shortValue());
    }

    @Test
    public void anInteger() {
        Assert.assertEquals(5, REPO.anInteger("a.int").intValue());
    }

    @Test
    public void aLong() {
        Assert.assertEquals(6, REPO.aLong("a.long").longValue());
    }

    @Test
    public void aBigDecimal() {
        Assert.assertEquals(new BigDecimal("33.33"), REPO.aBigDecimal("a.bigdec").get());
    }
}
