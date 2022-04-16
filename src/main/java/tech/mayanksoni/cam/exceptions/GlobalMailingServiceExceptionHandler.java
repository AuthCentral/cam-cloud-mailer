package tech.mayanksoni.cam.exceptions;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static tech.mayanksoni.cam.constants.MailerServiceExceptionResponses.REQUEST_IS_INVALID;
import static tech.mayanksoni.cam.constants.MailerServiceExceptionResponses.SANITY_RESPONSE_ERROR;

@ControllerAdvice
public class GlobalMailingServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailSanityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> processEmailSanityResponse(WebRequest request, EmailSanityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(buildSanityExceptionMessage(ex.getEmailDomain()))
                .build());
    }

    @ExceptionHandler(RequestInvalid.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequest(WebRequest request, RequestInvalid ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(MessageFormat.format(REQUEST_IS_INVALID, ex.getMessage()))
                .build());
    }

    @ExceptionHandler(MailerServiceException.class)
    public ResponseEntity<ExceptionResponse> handleServiceException(MailerServiceException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .build());
    }

    private String buildSanityExceptionMessage(String emailId) {
        if (!StringUtils.isBlank(emailId)) {
            return MessageFormat.format(SANITY_RESPONSE_ERROR, emailId);
        }
        return SANITY_RESPONSE_ERROR;
    }
}
