package tech.mayanksoni.cam.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VerificationRequest {
    private String transactionCode;
    private String emailId;
    private String verificationCode;
    private String correlationId;



}
