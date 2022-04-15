package tech.mayanksoni.cam.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailerSupportContent {
    private String processedTemplate;
    private String transactionCode;
    private String emailAddress;
    private int responseCode;

}