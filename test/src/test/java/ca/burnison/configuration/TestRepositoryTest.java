package ca.burnison.configuration;

import org.junit.Assert;
import org.junit.Test;

public final class TestRepositoryTest {

    @Test
    public void empty() {
        Assert.assertNull(TestRepository.empty().aString("c").get());
    }

    @Test
    public void initially() {
        Assert.assertEquals("C", TestRepository.initially("c", "C").aString("c").get());
    }

    @Test
    public void with() {
        final TestRepository repo = TestRepository.initially("c", "C").with("d", "D");
        Assert.assertTrue(repo.aString("c").isPresent());
        Assert.assertEquals("C", repo.aString("c").get());

        Assert.assertTrue(repo.aString("d").isPresent());
        Assert.assertEquals("D", repo.aString("d").get());

        repo.with("c", "Z");
        Assert.assertEquals("Property should have been updated.", "Z", repo.aString("c").get());
    }

    @Test
    public void without() {
        final TestRepository repo = TestRepository.initially("c", "C").without("c");
        Assert.assertNull(repo.aString("c").get());
        Assert.assertFalse(repo.aString("c").isPresent());
    }
}
