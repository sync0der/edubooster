package uz.tsue.ricoin.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorResponseDto {
    private int status;
    private String errorMessage;
    @Builder.Default()
    private long timestamp = new Date().getTime();
}
