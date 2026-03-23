package com.ynov.guilde.application.usecase;

import com.ynov.guilde.application.dto.AventurierResponseDto;
import com.ynov.guilde.application.mapper.AventurierMapper;
import com.ynov.guilde.domain.repository.AventurierRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListerAventuriersUseCase {
    private final AventurierRepository repository;
    private final AventurierMapper mapper;

    public ListerAventuriersUseCase(AventurierRepository repository, AventurierMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AventurierResponseDto> executer() {
        return repository.findAll().stream()
                .map(mapper::toDto) 
                .toList();
    }
}