package tech.mayanksoni.cam.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.mayanksoni.cam.exceptions.EmailSanityException;
import tech.mayanksoni.cam.requests.MailingRequest;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailingServiceResponse {
    private String requestingService;
    private EmailSanityException sanityException;
    private String emailIdentifier;
    private int apiResponseCode;

}
