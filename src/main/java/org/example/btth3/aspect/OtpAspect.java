package org.example.btth3.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OtpAspect {

    private static final String VALID_OTP = "123456";

    @Before("@annotation(org.example.btth3.annotation.RequireOtp) && args(amount, currency, otp)")
    public void validateOtp(Double amount, String currency, String otp) {
        if (otp == null || !VALID_OTP.equals(otp)) {
            throw new SecurityException("OTP không hợp lệ. Giao dịch bị từ chối.");
        }
    }
}
