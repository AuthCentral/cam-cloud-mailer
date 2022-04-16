package tech.mayanksoni.cam.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SanityServiceResponse {
    private String failingSanityRule;
    private Boolean sanityServiceResponse;

}
