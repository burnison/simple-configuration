package ca.burnison.configuration;

public final class PropertyMissingException extends IllegalStateException {
    private static final long serialVersionUID = 1;

    PropertyMissingException(final String message) {
        super(message);
    }
}
