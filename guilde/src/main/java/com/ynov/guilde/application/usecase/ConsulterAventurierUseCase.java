package com.ynov.guilde.application.usecase;

import com.ynov.guilde.application.dto.AventurierResponseDto;
import com.ynov.guilde.application.mapper.AventurierMapper;
import com.ynov.guilde.domain.exception.AventurierNonTrouveException;
import com.ynov.guilde.domain.repository.AventurierRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ConsulterAventurierUseCase {
    private final AventurierRepository repository;
    private final AventurierMapper mapper;

    public ConsulterAventurierUseCase(AventurierRepository repository, AventurierMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AventurierResponseDto executer(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new AventurierNonTrouveException(id)); // Lève l'erreur si non trouvé
    }
}