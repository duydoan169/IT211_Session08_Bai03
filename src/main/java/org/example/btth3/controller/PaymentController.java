package org.example.btth3.controller;

import org.example.btth3.dto.PaymentRequest;
import org.example.btth3.dto.RefundRequest;
import org.example.btth3.entity.Transaction;
import org.example.btth3.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/domestic")
    public ResponseEntity<Transaction> domestic(@Valid @RequestBody PaymentRequest request) {
        Transaction transaction = paymentService.processDomesticPayment(request.getAmount(), request.getCurrency());
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/international")
    public ResponseEntity<Transaction> international(
            @RequestHeader("X-OTP") String otp,
            @Valid @RequestBody PaymentRequest request) {

        Transaction transaction = paymentService.processInternationalPayment(request.getAmount(), request.getCurrency(), otp);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/refund")
    public ResponseEntity<Transaction> refund(
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody RefundRequest request) {

        Transaction transaction = paymentService.processRefund(request.getTransactionCode(), request.getAmount(), role);
        return ResponseEntity.ok(transaction);
    }
}
