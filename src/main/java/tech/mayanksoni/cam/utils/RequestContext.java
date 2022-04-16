package tech.mayanksoni.cam.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import tech.mayanksoni.cam.requests.VerificationRequest;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
@RequestScope
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestContext {
    private Map<String, String> headersMap;
    private String requestUrl;
    private String method;
    private HttpSession httpSession;

    public VerificationRequest toVerificationRequest() {
        return VerificationRequest.builder()

                .build();
    }
}
