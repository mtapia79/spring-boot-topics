package com.example.demo.dashboard.events;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long>{
    public void deleteAll();
    public Repo save(Repo e);
    public List<Repo> findAll();
    public Optional<Repo> findById(Long repoID);
}
