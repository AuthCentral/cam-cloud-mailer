package tech.mayanksoni.cam.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mayanksoni.cam.domains.MailingHistory;
import tech.mayanksoni.cam.repository.MailingHistoryRepository;
import tech.mayanksoni.cam.utils.MailerSupportContent;

import java.time.Instant;

@Service
@Slf4j
public class MailingDAOService {
    @Autowired
    private MailerSupportContent mailerSupportContent;
    @Autowired
    private MailingHistoryRepository mailingHistoryRepository;

    protected void saveMailingRequestAndResponse(){
        MailingHistory mailingHistory = MailingHistory.builder()
                .apiResponseCode(mailerSupportContent.getResponseCode())
                .apiResponseMessage(mailerSupportContent.getApiResponseBody())
                .id(mailerSupportContent.getEmailIdentifier())
                .transactionCode(mailerSupportContent.getTransactionCode())
                .requestPlacedInstant(Instant.now())
                .requestContext(mailerSupportContent.toString())
                .sentEmailAddress(mailerSupportContent.getEmailAddress())
                .build();
        mailingHistoryRepository.save(mailingHistory);
    }
}
