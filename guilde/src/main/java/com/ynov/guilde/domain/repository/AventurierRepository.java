package com.ynov.guilde.domain.repository;

import java.util.List;
import com.ynov.guilde.domain.model.Aventurier;
import java.util.Optional;
import java.util.UUID;

public interface AventurierRepository {
    Aventurier save(Aventurier aventurier);
    Optional<Aventurier> findById(UUID id);
    List<Aventurier> findAll();
    void deleteById(UUID id);
}