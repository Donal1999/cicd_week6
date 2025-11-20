package ie.atu.week5_githubactions.controller.errorHandling;

public class DuplicateError extends RuntimeException {
    private String message;
    private String field;
    public DuplicateError(String message, String field) {
        this.field = field;
    }
    public DuplicateError(String message) {
        super(message);
    }
}
