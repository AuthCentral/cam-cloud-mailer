package tech.mayanksoni.cam.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.mayanksoni.cam.constants.MailerServiceExceptionConstants;
import tech.mayanksoni.cam.exceptions.EmailSanityException;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static tech.mayanksoni.cam.constants.MailerServiceConstants.BLOCKLIST_SOURCE;
import static tech.mayanksoni.cam.constants.MailerServiceExceptionResponses.SANITY_RESPONSE_ERROR;

/**
 * This service is used to validate the recipient email is passing all the sanity checks and should be allowed to registered or not.
 */
@Service
@Slf4j
@EnableScheduling
public class SanityService {
    private List<String> suspectedDomainsList;

    @PostConstruct
    private void initComponents() {
        this.suspectedDomainsList = initializeSuspectedDomainList();
        log.info("Suspected domains size {}", this.suspectedDomainsList.size());
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void runMaintenanceTaskEveryFifteenMinutes() {
        log.info("Updating suspected domains from source");
        this.suspectedDomainsList = initializeSuspectedDomainList();
    }

    public void processSanityRules(String emailId) throws EmailSanityException {
        //Check Email Domain=
        checkEmailDomain(emailId);

    }

    private void checkEmailDomain(String emailId) throws EmailSanityException {
        String domainName = emailId.substring(emailId.indexOf("@") + 1);
        log.info("Processing Email Domain Rules for {}", domainName);
        if (suspectedDomainsList.contains(domainName.toLowerCase())) {
            EmailSanityException ex = new EmailSanityException(MailerServiceExceptionConstants.SANITY_VERIFICATION_FAILED.responseMessage);
            ex.setEmailDomain(domainName);
            throw ex;
        }
    }

    protected List<String> initializeSuspectedDomainList() {
        Instant startInstant = Instant.now();
        WebClient httpClient = WebClient.builder().baseUrl(BLOCKLIST_SOURCE).defaultHeader(HttpHeaders.CONTENT_TYPE).defaultUriVariables(Collections.singletonMap("url", BLOCKLIST_SOURCE))
                .build();
        Mono<String> response = httpClient.get().exchangeToMono(clientResponse -> {
            if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                return clientResponse.bodyToMono(String.class);
            } else {
                log.error(SANITY_RESPONSE_ERROR);
                return clientResponse.createException().flatMap(Mono::error);
            }
        });
        List<String> bannedDomains = Arrays.asList(response.block().split("\n")).stream().map(domainName -> domainName.toLowerCase()).collect(Collectors.toList());
        log.info("Update took {}", Duration.between(startInstant, Instant.now()));
        return bannedDomains;
    }

}
