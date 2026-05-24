package org.example.btth3.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ManagerApprovalAspect {

    @Before("@annotation(org.example.btth3.annotation.RequireManagerApproval) && args(transactionCode, amount, role)")
    public void validateManagerRole(String transactionCode, Double amount, String role) {
        if (!"MANAGER".equalsIgnoreCase(role)) {
            throw new SecurityException("Truy cập bị từ chối. Chỉ MANAGER mới được phép hoàn tiền.");
        }
    }
}