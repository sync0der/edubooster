package uz.tsue.ricoin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.tsue.ricoin.dto.response.EventDto;
import uz.tsue.ricoin.entity.Event;
import uz.tsue.ricoin.service.PromoCodeGeneratorService;
import uz.tsue.ricoin.service.UtilsService;

@Component
@RequiredArgsConstructor
public class EventMapper implements CustomMapperInterface<Event, EventDto> {
    private final PromoCodeGeneratorService promoCodeGeneratorService;
    private final UtilsService utilsService;

    @Override
    public EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .place(event.getPlace())
                .price(event.getPrice())
                .dateTime(event.getDateTime().toString())
                .isActive(event.isActive())
                .promoCode(event.getPromoCode())
                .build();
    }

    @Override
    public Event toEntity(EventDto eventDto) {
        return Event.builder()
                .name(eventDto.name())
                .description(eventDto.description())
                .price(eventDto.price())
                .place(eventDto.place())
                .dateTime(utilsService.getFormattedDateTime(eventDto.dateTime()))
                .isActive(true)
                .promoCode(promoCodeGeneratorService.generatePromoCode(10))
                .build();
    }
}
