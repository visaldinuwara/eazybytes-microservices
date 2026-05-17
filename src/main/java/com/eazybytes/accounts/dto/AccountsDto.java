package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digit")
    @Schema(
        description = "Account Numbe of Eazy Bank account",
        example = "2565434321"
    )
    private Long accountNumber;
    @NotEmpty(message ="Account number can not be a null or empty")
        @Schema(
        description = "Account type of Eazy Bank account",
        example = "Savings"
    )
    private String accountType;
    @NotEmpty(message = "Branch Address can not be a null or empty")
        @Schema(
        description = "Account Numbe of Eazy Bank account"
    )
    private String branchAddress;
}
