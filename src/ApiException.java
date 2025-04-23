public class ApiException extends RuntimeException {
    String message;
    public ApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
