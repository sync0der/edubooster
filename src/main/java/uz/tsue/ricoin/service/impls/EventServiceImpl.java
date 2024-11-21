package uz.tsue.ricoin.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsue.ricoin.dto.response.EventDto;
import uz.tsue.ricoin.entity.Event;
import uz.tsue.ricoin.mapper.EventMapper;
import uz.tsue.ricoin.repository.EventRepository;
import uz.tsue.ricoin.service.PromoCodeGeneratorService;
import uz.tsue.ricoin.service.UtilsService;
import uz.tsue.ricoin.service.interfaces.EventService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PromoCodeGeneratorService promoCodeGeneratorService;
    private final UtilsService utilsService;
    private final EventMapper eventMapper;

    @Override
    public EventDto get(Long id) {
        Event event = findById(id);
        return eventMapper.toDto(event);
    }

    @Override
    public List<EventDto> getAll() {
        List<EventDto> events = new ArrayList<>();
        List<Event> list = eventRepository.findAll();
        for (Event event : list) {
            events.add(eventMapper.toDto(event));
        }
        return events;
    }

    private Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public EventDto create(EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    @Override
    public void update(Long id, EventDto eventDto) {
        Event event = findById(id);
        Optional.ofNullable(eventDto.name()).ifPresent(event::setName);
        Optional.ofNullable(eventDto.description()).ifPresent(event::setDescription);
        Optional.ofNullable(eventDto.place()).ifPresent(event::setPlace);
        Optional.ofNullable(eventDto.price()).ifPresent(event::setPrice);
        event.setPromoCode(promoCodeGeneratorService.generatePromoCode(10));
        if (eventDto.dateTime() != null)
            event.setDateTime(utilsService.getFormattedDateTime(eventDto.dateTime()));

        eventRepository.save(event);
    }

    @Override
    public void updatePromoCode(Long id) {
        Event event = findById(id);
        event.setPromoCode(promoCodeGeneratorService.generatePromoCode(10));
        eventRepository.save(event);
    }

    @Override
    public void remove(Long id) {
        eventRepository.deleteById(id);
    }

}
