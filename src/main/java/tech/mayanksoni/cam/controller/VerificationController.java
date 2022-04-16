package tech.mayanksoni.cam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.mayanksoni.cam.exceptions.MailerServiceException;
import tech.mayanksoni.cam.requests.VerificationRequest;
import tech.mayanksoni.cam.services.VerificationService;

@RestController
@RequestMapping("/verify")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;

    @GetMapping("/email")
    public ResponseEntity<Void> handleRequestForEmailVerification(@RequestParam("emailId") String emailId, @RequestParam("transactionCode") String transactionCode, @RequestParam("verificationCode") String verificationCode, @RequestParam("emailIdentifier") String identifier) throws MailerServiceException {
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", verificationService.verifyAndRedirectToCAMManager(VerificationRequest.builder()
                .emailId(emailId)
                .verificationCode(verificationCode)
                .transactionCode(transactionCode)
                .correlationId(identifier)
                .build())).build();
    }
}
