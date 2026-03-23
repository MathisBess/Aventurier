package com.ynov.guilde.application.usecase;

import com.ynov.guilde.domain.exception.AventurierNonTrouveException;
import com.ynov.guilde.domain.repository.AventurierRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SupprimerAventurierUseCase {
    private final AventurierRepository repository;

    public SupprimerAventurierUseCase(AventurierRepository repository) {
        this.repository = repository;
    }

    public void executer(UUID id) {
        if (repository.findById(id).isEmpty()) {
            throw new AventurierNonTrouveException(id);
        }
        repository.deleteById(id);
    }
}