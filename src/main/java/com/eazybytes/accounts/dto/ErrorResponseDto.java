package com.eazybytes.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
    name="ErrorResponse",
    description = "Schema to hold error response infomation"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
    description = "API path invoked by client"
)
    private String apiPath;
        @Schema(
    description = "Error code represented in error happened"
)
    private HttpStatus errorCode;
        @Schema(
    description = "Error message invoked by client"
)
    private String errorMessage;
    private LocalDateTime errorTime;
}
