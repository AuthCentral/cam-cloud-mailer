package tech.mayanksoni.cam.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.Instant;

@Document("mailing-history-collection")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailingHistory {
    @Id
    private String id;
    private String sentEmailAddress;
    private Instant requestPlacedInstant;
    private String apiResponseMessage;
    private int apiResponseCode;
    private String transactionCode;
    private String requestContext;
    private String verificationLink;
    private String verificationCode;
    private Duration transactionDuration;


}
