package uz.tsue.ricoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.tsue.ricoin.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
