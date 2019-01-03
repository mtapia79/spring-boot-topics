package com.example.demo.dashboard.events;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    public void deleteAll();
    public Actor save(Actor e);
    public List<Actor> findAll();
    public Optional<Actor> findById(Long actorID);
}
