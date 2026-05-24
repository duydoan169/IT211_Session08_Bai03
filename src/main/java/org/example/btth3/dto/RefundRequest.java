package org.example.btth3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RefundRequest {

    @NotBlank(message = "Mã giao dịch không được để trống")
    @Pattern(
            regexp = "^[a-zA-Z0-9_-]+$",
            message = "Mã giao dịch chỉ được chứa chữ cái, số, dấu gạch dưới và gạch ngang"
    )
    private String transactionCode;

    @NotNull(message = "Số tiền không được để trống")
    @Positive(message = "Số tiền phải lớn hơn 0")
    private Double amount;
}