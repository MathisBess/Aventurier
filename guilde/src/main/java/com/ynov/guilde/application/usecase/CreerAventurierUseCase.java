package com.ynov.guilde.application.usecase;

import com.ynov.guilde.application.dto.AventurierRequestDto;
import com.ynov.guilde.application.dto.AventurierResponseDto;
import com.ynov.guilde.application.mapper.AventurierMapper;
import com.ynov.guilde.domain.model.Aventurier;
import com.ynov.guilde.domain.repository.AventurierRepository;
import org.springframework.stereotype.Service;

@Service
public class CreerAventurierUseCase {

    private final AventurierRepository repository;
    private final AventurierMapper mapper;

    public CreerAventurierUseCase(AventurierRepository repository, AventurierMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AventurierResponseDto executer(AventurierRequestDto dto) {

        Aventurier nouvelAventurier = mapper.toDomain(dto);
        
        Aventurier aventurierSauvegarde = repository.save(nouvelAventurier);
        
        return mapper.toDto(aventurierSauvegarde);
    }
}