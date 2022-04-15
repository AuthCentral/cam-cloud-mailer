package tech.mayanksoni.cam.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tech.mayanksoni.cam.requests.MailingRequest;
import tech.mayanksoni.cam.utils.MailerSupportContent;

@Service
@Slf4j
public class TemplatingEngine {
    @Autowired
    private MailerSupportContent mailerSupportContent;
    @Autowired
    private TemplateEngine templateEngine;


    public void processEmailContent(MailingRequest request) {
        Context mailContext = new Context();
        mailContext.setVariable("request", request);
        mailContext.setVariable("supporter",mailerSupportContent);
        switch (request.getMailType()) {
            case EMAIL_VERIFICATION:
                mailerSupportContent.setProcessedTemplate(templateEngine.process("emailVerification.html",mailContext));
                break;
        }
    }
}
