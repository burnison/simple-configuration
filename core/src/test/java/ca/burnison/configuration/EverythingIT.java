package ca.burnison.configuration;

import java.util.concurrent.atomic.AtomicLong;

public final class EverythingIT {

    public static void main(final String[] args) throws Exception {
        final Source source = ChainedSource.linking(
            new SystemPropertiesSource(),
            new EnvVarSource()
        );

        final AtomicLong ctr = new AtomicLong();
        final Repository repo = new Repository(source);
        final BigDecimalProperty p = repo.aBigDecimal("foo");

        while(1 < Integer.parseInt("2")) {
            System.out.println(p.get());
            Thread.sleep(1000);
            System.setProperty("foo", Long.toString(ctr.getAndIncrement()));
        }
    }
}
