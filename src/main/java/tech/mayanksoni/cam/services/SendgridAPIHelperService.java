package tech.mayanksoni.cam.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mayanksoni.cam.constants.MailerServiceExceptionConstants;
import tech.mayanksoni.cam.exceptions.SendgridAPIRequestFailed;
import tech.mayanksoni.cam.requests.MailingRequest;
import tech.mayanksoni.cam.utils.MailerSupportContent;

import java.io.IOException;
import java.text.MessageFormat;

import static tech.mayanksoni.cam.constants.MailerServiceConstants.CAM_EMAIL_VERIFICATION_SUBJECT;
import static tech.mayanksoni.cam.constants.MailerServiceConstants.CAM_FROM_EMAIL_ADDRESS;

@Service
@Slf4j
public class SendgridAPIHelperService {
    @Autowired
    private SendGridAPI sendGridAPI;
    @Autowired
    private MailerSupportContent mailerSupportContent;

    void sendEmail(MailingRequest request) throws SendgridAPIRequestFailed {
        Email from = new Email(CAM_FROM_EMAIL_ADDRESS);
        String subject = MessageFormat.format(CAM_EMAIL_VERIFICATION_SUBJECT, request.getDestinationEmailAddress());
        Email toEmail = new Email(request.getDestinationEmailAddress());
        Content emailBodyContent = new Content("text/html", mailerSupportContent.getProcessedTemplate());
        Mail mail = new Mail(from, subject, toEmail, emailBodyContent);
        try {
            Request apiRequest = new Request();
            apiRequest.setMethod(Method.POST);
            apiRequest.setEndpoint("mail/send");
            apiRequest.setBody(mail.build());
            Response apiResponse = sendGridAPI.api(apiRequest);
            mailerSupportContent.setResponseCode(apiResponse.getStatusCode());
            if (!(apiResponse.getStatusCode() == 200 || apiResponse.getStatusCode() == 201 || apiResponse.getStatusCode() == 202)) {
                log.error(apiResponse.getBody());
                throw new SendgridAPIRequestFailed(MessageFormat.format(MailerServiceExceptionConstants.SENDGRID_API_FAILED.responseMessage,apiResponse.getStatusCode()));
            }
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

    }
}
