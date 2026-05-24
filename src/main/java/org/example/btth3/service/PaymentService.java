package org.example.btth3.service;

import org.example.btth3.annotation.RequireManagerApproval;
import org.example.btth3.annotation.RequireOtp;
import org.example.btth3.entity.Transaction;
import org.example.btth3.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction processDomesticPayment(Double amount, String currency) {
        Transaction transaction = Transaction.builder()
                .transactionCode(UUID.randomUUID().toString())
                .amount(amount)
                .currency(currency)
                .type("DOMESTIC")
                .build();
        return transactionRepository.save(transaction);
    }

    @RequireOtp
    @Transactional
    public Transaction processInternationalPayment(Double amount, String currency, String otp) {
        Transaction transaction = Transaction.builder()
                .transactionCode(UUID.randomUUID().toString())
                .amount(amount)
                .currency(currency)
                .type("INTERNATIONAL")
                .build();
        return transactionRepository.save(transaction);
    }

    @RequireManagerApproval
    @Transactional
    public Transaction processRefund(String transactionCode, Double amount, String role) {
        transactionRepository.findByTransactionCode(transactionCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch: " + transactionCode));

        Transaction refund = Transaction.builder()
                .transactionCode(UUID.randomUUID().toString())
                .amount(amount)
                .currency("VND")
                .type("REFUND")
                .build();
        return transactionRepository.save(refund);
    }
}
