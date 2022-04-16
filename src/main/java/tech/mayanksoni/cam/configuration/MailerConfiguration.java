package tech.mayanksoni.cam.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class MailerConfiguration {
    @Value("${cam.api.url}")
    private String camApiURL;

    public String getCamApiURL() {
        return camApiURL;
    }
}
