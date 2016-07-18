package ca.burnison.configuration;

import java.util.function.Function;

/**
 * A functional interface that may be used to transform a key. A typical
 * use-case would be mapping envvars, which are typically uppercase into
 * the same casing and naming as all other variables.
 */
public interface KeyTransformer extends Function<String, String> {
}
