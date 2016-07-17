package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class ChainedSourceTest {

    @Test(expected = NullPointerException.class)
    public void new_primaryNull_npe() {
        ChainedSource.linking(null, FakeSource.create());
    }

    @Test(expected = NullPointerException.class)
    public void new_secondaryNull_npe() {
        ChainedSource.linking(FakeSource.create(), null);
    }

    @Test(expected = NullPointerException.class)
    public void new_alternateNull_npe() {
        ChainedSource.linking(FakeSource.create(), FakeSource.create(), (Source[]) null);
    }

    @Test
    public void get() {
        final Source chain = ChainedSource.linking(
            FakeSource.create().with("a", "a"),
            FakeSource.create().with("b", "b"),
            FakeSource.create().with("c", "c"),
            FakeSource.create().with("c", "z")
        );
        Assert.assertEquals("a", chain.get("a"));
        Assert.assertEquals("b", chain.get("b"));
        Assert.assertEquals("c", chain.get("c"));
        Assert.assertNull(chain.get("d"));
    }

    @Test
    public void getOrDefault() {
        final Source chain = ChainedSource.linking(
            FakeSource.create().with("a", "a"),
            FakeSource.create().with("b", "b"),
            FakeSource.create().with("c", "c"),
            FakeSource.create().with("c", "z")
        );
        Assert.assertEquals("a", chain.getOrDefault("a", "missing"));
        Assert.assertEquals("b", chain.getOrDefault("b", "missing"));
        Assert.assertEquals("c", chain.getOrDefault("c", "missing"));
        Assert.assertEquals("missing", chain.getOrDefault("d", "missing"));
    }

    @Test
    public void contains() {
        final Source chain = ChainedSource.linking(
            FakeSource.create().with("a", "a"),
            FakeSource.create().with("b", "b")
        );
        Assert.assertTrue(chain.contains("a"));
        Assert.assertTrue(chain.contains("b"));
        Assert.assertFalse(chain.contains("c"));
    }
}
