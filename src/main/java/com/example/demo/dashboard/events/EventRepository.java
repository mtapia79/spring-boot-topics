package com.example.demo.dashboard.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public interface EventRepository extends JpaRepository<Event, Long> , EventRepositoryCustom2 {
    public void deleteAll();
    public Event save(Event e);
    public Optional<Event> findById(Long id);
    public List<Event> findAll();
    
}

interface EventRepositoryCustom2 {
    List<Event> findByActorId(Long actorId);
}

@Repository
@Transactional(readOnly = true)
class EventRepositoryCustomImpl implements EventRepositoryCustom2 {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Event> findByActorId(Long actorId) {
        Query query = entityManager.createNativeQuery("SELECT ev.* FROM event as ev " +
                "WHERE ev.actor_id LIKE ?", Event.class);
        query.setParameter(1, actorId + "%");
        return query.getResultList();
    }
}