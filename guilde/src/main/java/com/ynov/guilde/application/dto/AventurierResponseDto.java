package com.ynov.guilde.application.dto;

import com.ynov.guilde.domain.model.Classe;
import java.util.UUID;

public record AventurierResponseDto(
    UUID id,
    String nom,
    String description,
    int physique,
    int mental,
    int perception,
    int niveau,
    Classe classe
) {}