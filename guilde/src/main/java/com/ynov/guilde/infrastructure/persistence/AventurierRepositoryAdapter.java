package com.ynov.guilde.infrastructure.persistence;

import java.util.List;
import com.ynov.guilde.domain.model.Aventurier;
import com.ynov.guilde.domain.repository.AventurierRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AventurierRepositoryAdapter implements AventurierRepository {

    private final AventurierJpaRepository jpaRepository;

    public AventurierRepositoryAdapter(AventurierJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Aventurier save(Aventurier aventurier) {
        AventurierEntity entity = new AventurierEntity(
                aventurier.getId(),
                aventurier.getNom(),
                aventurier.getDescription(),
                aventurier.getPhysique(),
                aventurier.getMental(),
                aventurier.getPerception(),
                aventurier.getNiveau(),
                aventurier.getClasse()
        );

        AventurierEntity savedEntity = jpaRepository.save(entity);

        return aventurier;
    }

    @Override
    public Optional<Aventurier> findById(UUID id) {
        return jpaRepository.findById(id).map(entity -> 
            new Aventurier(entity.getId(), entity.getNom(), entity.getDescription(), 
                           entity.getPhysique(), entity.getMental(), entity.getPerception(), 
                           entity.getNiveau(), entity.getClasse())
        );
    }

    @Override
    public List<Aventurier> findAll() {
        return jpaRepository.findAll().stream()
            .map(entity -> new Aventurier(entity.getId(), entity.getNom(), entity.getDescription(), 
                                          entity.getPhysique(), entity.getMental(), entity.getPerception(), 
                                          entity.getNiveau(), entity.getClasse()))
            .toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}