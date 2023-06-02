package org.velonation.core.exception;

public record ConstraintViolation(String fieldName, String errorMessage) {
    public static ConstraintViolation fromJakartaConstraintViolation(jakarta.validation.ConstraintViolation<?> violation) {
        return new ConstraintViolation(violation.getPropertyPath().toString(), violation.getMessage());
    }
}
