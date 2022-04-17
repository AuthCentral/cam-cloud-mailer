package tech.mayanksoni.cam.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mayanksoni.cam.configuration.MailerConfiguration;
import tech.mayanksoni.cam.domains.MailingHistory;
import tech.mayanksoni.cam.exceptions.MailerServiceException;
import tech.mayanksoni.cam.exceptions.ResourceNotFoundException;
import tech.mayanksoni.cam.requests.VerificationRequest;
import tech.mayanksoni.cam.utils.GeneratorUtils;
import tech.mayanksoni.cam.utils.URLUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class VerificationService {
    @Autowired
    private MailingDAOService mailingDAOService;
    @Autowired
    private MailerConfiguration mailerConfiguration;


    public String verifyAndRedirectToCAMManager(VerificationRequest verificationRequest) throws MailerServiceException {
        try {
            MailingHistory history = mailingDAOService.getMailingHistory(verificationRequest.getCorrelationId());
            if (history.getTransactionCode().equals(verificationRequest.getTransactionCode()) && history.getVerificationCode().equals(verificationRequest.getVerificationCode())) {
                mailingDAOService.deleteUsedTokens(history);
                return createRedirectToken();
            }
        } catch (ResourceNotFoundException e) {
            throw new MailerServiceException(e.getMessage());
        }
        return null;

    }

    private String createRedirectToken() {
        String camAppUrl = mailerConfiguration.getCamManagerBaseUrl() + "/verify/email";
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("token", GeneratorUtils.buildRandomAlphanumericSequence(200));
        return URLUtils.encodeParams(requestMap, camAppUrl);
    }
}
