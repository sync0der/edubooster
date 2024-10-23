package uz.tsue.ricoin.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PromoCodeGeneratorService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
    private static final SecureRandom random = new SecureRandom();

    public String generatePromoCode(int length) {
        StringBuilder promoCode = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            promoCode.append(
                    CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
            );
        }
        return promoCode.toString();
    }
}
