package org.velonation.core.controller.api.v1;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.velonation.core.exception.ConstraintViolation;
import java.util.List;

@Builder @Getter
public class ResponseBody {
    HttpStatusCode statusCode;
    List<ConstraintViolation> violations;
    String message;
}