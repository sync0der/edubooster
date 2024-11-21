package uz.tsue.ricoin.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String firstName;
    private String lastName;
    private String groupName;
    private String phoneNumber;
}
