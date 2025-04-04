package confidentiality.exception;

import confidentiality.model.ErrorObject;

public class APICallException extends RuntimeException {
    private final ErrorObject errorObject;
    public APICallException(ErrorObject error) {
        super(error.message());
        this.errorObject = error;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }
}
