package com.ynov.guilde.application.usecase;

import com.ynov.guilde.application.dto.AventurierResponseDto;
import com.ynov.guilde.application.mapper.AventurierMapper;
import com.ynov.guilde.domain.exception.AventurierNonTrouveException;
import com.ynov.guilde.domain.model.Aventurier;
import com.ynov.guilde.domain.repository.AventurierRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class FaireMonterNiveauUseCase {
    private final AventurierRepository repository;
    private final AventurierMapper mapper;

    public FaireMonterNiveauUseCase(AventurierRepository repository, AventurierMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AventurierResponseDto executer(UUID id) {
        Aventurier aventurier = repository.findById(id)
                .orElseThrow(() -> new AventurierNonTrouveException(id));
        
        aventurier.monterDeNiveau(); 
        
        return mapper.toDto(repository.save(aventurier));
    }
}