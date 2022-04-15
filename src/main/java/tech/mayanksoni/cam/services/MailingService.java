package tech.mayanksoni.cam.services;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mayanksoni.cam.constants.MailerServiceExceptionConstants;
import tech.mayanksoni.cam.exceptions.EmailSanityException;
import tech.mayanksoni.cam.exceptions.RequestInvalid;
import tech.mayanksoni.cam.exceptions.SendgridAPIRequestFailed;
import tech.mayanksoni.cam.requests.MailingRequest;
import tech.mayanksoni.cam.response.MailingServiceResponse;
import tech.mayanksoni.cam.utils.GeneratorUtils;
import tech.mayanksoni.cam.utils.MailerSupportContent;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MailingService {
    @Autowired
    private SanityService sanityService;
    @Autowired
    private TemplatingEngine templatingEngine;
    @Autowired
    private SendgridAPIHelperService sendgridAPIHelperService;
    @Autowired
    private MailerSupportContent mailerSupportContent;


    public MailingServiceResponse sendEmail(MailingRequest request) throws EmailSanityException, RequestInvalid, SendgridAPIRequestFailed {
        LocalDateTime dateTime = LocalDateTime.now();
        validateIncomingRequest(request);
        sanityService.processSanityRules(request.getDestinationEmailAddress());
        mailerSupportContent.setTransactionCode(GeneratorUtils.buildRandomAlphanumericSequence(200));
        templatingEngine.processEmailContent(request);
        sendgridAPIHelperService.sendEmail(request);
        return MailingServiceResponse.builder()
                .emailIdentifier(GeneratorUtils.buildRandomAlphanumericSequence(50))
                .apiResponseCode(mailerSupportContent.getResponseCode())
                .build();
    }

    private void validateIncomingRequest(MailingRequest request) throws RequestInvalid {
        if (StringUtils.isBlank(request.getDestinationEmailAddress())) {
            throw new RequestInvalid(MailerServiceExceptionConstants.DESTINATION_EMAIL_NOT_PRESENT.responseMessage);
        } else if (StringUtils.isBlank(request.getTransactionCode())) {
            throw new RequestInvalid(MailerServiceExceptionConstants.TRANSACTION_CODE_IS_EMPTY.responseMessage);
        } else if (request.getMailType() == null) {
            throw new RequestInvalid(MailerServiceExceptionConstants.MAIL_TYPE_NOT_PRESENT.responseMessage);
        }
    }
}
