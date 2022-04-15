package tech.mayanksoni.cam.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.mayanksoni.cam.constants.MailType;
import tech.mayanksoni.cam.exceptions.EmailSanityException;
import tech.mayanksoni.cam.exceptions.RequestInvalid;
import tech.mayanksoni.cam.exceptions.SendgridAPIRequestFailed;
import tech.mayanksoni.cam.requests.MailingRequest;
import tech.mayanksoni.cam.utils.GeneratorUtils;
import tech.mayanksoni.cam.utils.MailerSupportContent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailingServiceTest {
    @Mock
    SanityService sanityService;
    @Mock
    TemplatingEngine templatingEngine;
    @Mock
    SendgridAPIHelperService sendgridAPIHelperService;
    @Mock
    MailerSupportContent mailerSupportContent;
    @InjectMocks
    MailingService mailingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void sendEmail() throws EmailSanityException, SendgridAPIRequestFailed, RequestInvalid {
        MailingRequest mockedMailingRequest = MailingRequest.builder()
                .firstName("Test")
                .attachmentRequired(false)
                .transactionCode(GeneratorUtils.buildRandomAlphanumericSequence(50))
                .destinationEmailAddress("test@google.com")
                .mailType(MailType.EMAIL_VERIFICATION)
                .requestingService("requestingService")
                .attachmentIdentifier("")
                .build();
        mailingService.sendEmail(mockedMailingRequest);
    }
}