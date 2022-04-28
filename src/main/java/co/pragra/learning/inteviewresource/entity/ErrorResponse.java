package co.pragra.learning.inteviewresource.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private String msg;
    private int code;
    private Instant errorTime;
}
