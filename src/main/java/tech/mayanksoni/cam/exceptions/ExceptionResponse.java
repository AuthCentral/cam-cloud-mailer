package tech.mayanksoni.cam.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;

}
