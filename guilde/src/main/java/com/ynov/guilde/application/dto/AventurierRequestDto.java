package com.ynov.guilde.application.dto;

import com.ynov.guilde.domain.model.Classe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AventurierRequestDto(
    @NotBlank String nom,
    @Size(max = 500) String description,
    @Min(1) @Max(20) int physique,
    @Min(1) @Max(20) int mental,
    @Min(1) @Max(20) int perception,
    @NotNull Classe classe
) {}