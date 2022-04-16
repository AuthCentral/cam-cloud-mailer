package tech.mayanksoni.cam.services;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mayanksoni.cam.configuration.MailerConfiguration;
import tech.mayanksoni.cam.constants.MailerServiceExceptionConstants;
import tech.mayanksoni.cam.exceptions.EmailSanityException;
import tech.mayanksoni.cam.exceptions.RequestInvalid;
import tech.mayanksoni.cam.exceptions.SendgridAPIRequestFailed;
import tech.mayanksoni.cam.requests.MailingRequest;
import tech.mayanksoni.cam.response.MailingServiceResponse;
import tech.mayanksoni.cam.utils.GeneratorUtils;
import tech.mayanksoni.cam.utils.MailerSupportContent;
import tech.mayanksoni.cam.utils.URLUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static tech.mayanksoni.cam.constants.MailerServiceConstants.*;

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
    @Autowired
    private MailerConfiguration mailerConfiguration;
    @Autowired
    private MailingDAOService mailingDAOService;


    public MailingServiceResponse sendEmail(MailingRequest request) throws EmailSanityException, RequestInvalid, SendgridAPIRequestFailed {
        LocalDateTime dateTime = LocalDateTime.now();
        validateIncomingRequest(request);
        sanityService.processSanityRules(request.getDestinationEmailAddress());
        mailerSupportContent.setTransactionCode(GeneratorUtils.buildRandomAlphanumericSequence(TRANSACTION_CODE_LEN));
        mailerSupportContent.setVerificationCode(GeneratorUtils.buildRandomAlphanumericSequence(VERIFICATION_CODE_LEN));
        mailerSupportContent.setEmailIdentifier(GeneratorUtils.buildRandomAlphanumericSequence(EMAIL_MONGO_ID_LEN));
        mailerSupportContent.setEmailVerificationLink(buildVerificationLink());
        templatingEngine.processEmailContent(request);
        sendgridAPIHelperService.sendEmail(request);
        mailingDAOService.saveMailingRequestAndResponse();
        return MailingServiceResponse.builder()
                .emailIdentifier(mailerSupportContent.getEmailIdentifier())
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

    private String buildVerificationLink() {
        String verificationEndpoint  = "/mailer/verify/email";
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("emailId", mailerSupportContent.getEmailAddress());
        requestParams.put("transactionCode",mailerSupportContent.getTransactionCode());
        requestParams.put("verificationCode", mailerSupportContent.getVerificationCode());
        requestParams.put("emailIdentifier", mailerSupportContent.getEmailIdentifier());
        return URLUtils.encodeParams(requestParams, mailerConfiguration.getCamApiURL() + verificationEndpoint);
    }
}
