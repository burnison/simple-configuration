package ca.burnison.configuration;

public final class PropertyInvalidException extends IllegalStateException {
    private static final long serialVersionUID = 1;

    PropertyInvalidException(final String message) {
        super(message);
    }
}
