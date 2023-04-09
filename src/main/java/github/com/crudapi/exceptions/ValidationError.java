package github.com.crudapi.exceptions;

public record ValidationError(
        String field,
        String message

) {
}
