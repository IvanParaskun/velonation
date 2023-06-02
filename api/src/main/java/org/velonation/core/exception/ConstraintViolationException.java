package org.velonation.core.exception;

import lombok.Getter;
import java.util.Set;

@Getter
public class ConstraintViolationException extends Exception {
    private final Set<ConstraintViolation> violations;

    public ConstraintViolationException(Set<ConstraintViolation> violations) {
        this.violations = violations;
    }
}
