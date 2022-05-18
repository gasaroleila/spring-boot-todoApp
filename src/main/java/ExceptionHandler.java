import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ExceptionHandler{
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ExceptionHandler(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionHandler(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = List.of(error);
    }
}
