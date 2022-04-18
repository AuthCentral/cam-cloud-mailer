package tech.mayanksoni.cam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.mayanksoni.cam.exceptions.EmailSanityException;
import tech.mayanksoni.cam.exceptions.RequestInvalid;
import tech.mayanksoni.cam.exceptions.SendgridAPIRequestFailed;
import tech.mayanksoni.cam.requests.MailingRequest;
import tech.mayanksoni.cam.response.MailingServiceResponse;
import tech.mayanksoni.cam.services.MailingService;
import tech.mayanksoni.cam.utils.MailerSupportContent;

import java.time.Instant;

@RestController
@RequestMapping("/mail")
public class MailingServiceController {
    @Autowired
    private MailingService mailingService;
    @Autowired
    private MailerSupportContent mailerSupportContent;


    @PostMapping("/send")
    public ResponseEntity<MailingServiceResponse> handleRequestToSendEmail(@RequestBody MailingRequest request) throws EmailSanityException, RequestInvalid, SendgridAPIRequestFailed {
        mailerSupportContent.setTransactionStartInstant(Instant.now());
        mailerSupportContent.setEmailAddress(request.getDestinationEmailAddress());
        mailerSupportContent.setTransactionCode(request.getTransactionCode());
        return ResponseEntity.status(HttpStatus.OK).body(mailingService.sendEmail(request));
    }
}
