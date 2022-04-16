package tech.mayanksoni.cam.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.mayanksoni.cam.constants.MailType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailingRequest {
    private String requestingService;
    private String destinationEmailAddress;
    private String transactionCode;
    private Boolean attachmentRequired;
    private String attachmentIdentifier;
    private String firstName;
    private MailType mailType;

}
