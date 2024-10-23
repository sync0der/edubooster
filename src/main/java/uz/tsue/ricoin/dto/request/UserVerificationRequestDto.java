package uz.tsue.ricoin.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVerificationRequestDto {
    private String email;
    private String verificationCode;
}
