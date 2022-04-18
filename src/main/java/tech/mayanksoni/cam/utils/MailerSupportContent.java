package tech.mayanksoni.cam.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.Instant;

@Component
@RequestScope
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailerSupportContent {
    private String processedTemplate;
    private String transactionCode;
    private String emailAddress;
    private int responseCode;
    private String emailVerificationLink;
    private String verificationCode;
    private String emailIdentifier;
    private String apiResponseBody;
    private Instant transactionStartInstant;

}
