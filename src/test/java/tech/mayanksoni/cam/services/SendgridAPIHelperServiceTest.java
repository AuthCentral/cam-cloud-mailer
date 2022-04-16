package tech.mayanksoni.cam.services;

import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.mayanksoni.cam.exceptions.SendgridAPIRequestFailed;
import tech.mayanksoni.cam.requests.MailingRequest;
import tech.mayanksoni.cam.utils.GeneratorUtils;
import tech.mayanksoni.cam.utils.MailerSupportContent;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendgridAPIHelperServiceTest {
    @Mock
    SendGridAPI sendGridAPI;
    @Mock
    MailerSupportContent mailerSupportContent;
    @InjectMocks
    SendgridAPIHelperService sendgridAPIHelperService;

    @Test
    void sendEmail() throws Exception {
        Response sendgridSuccessResponse = new Response(200, "Sent", new HashMap<>());
        Response sendgridFailureResponse = new Response(401, "Unauthorized", new HashMap<>());
        when(sendGridAPI.api(any())).thenReturn(sendgridSuccessResponse).thenReturn(sendgridFailureResponse);
        when(mailerSupportContent.getProcessedTemplate()).thenReturn("JUnit Testing Response");
        MailingRequest mockRequest = MailingRequest.builder()
                .destinationEmailAddress("test@springMock.com")
                .transactionCode(GeneratorUtils.buildRandomAlphanumericSequence(50))
                .attachmentRequired(false)
                .firstName("Testing")
                .build();
        sendgridAPIHelperService.sendEmail(mockRequest);
        try {
            sendgridAPIHelperService.sendEmail(mockRequest);
        } catch (SendgridAPIRequestFailed ex) {

        }
    }

}