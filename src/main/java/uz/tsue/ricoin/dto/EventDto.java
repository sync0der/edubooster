package uz.tsue.ricoin.dto;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link uz.tsue.ricoin.entity.Event}
 */
@Builder
public record EventDto(String name, String description, String place, Integer prize,
                       String availableFrom, String availableTill, boolean isActive, String promoCode) implements Serializable {
}