package com.ynov.guilde.application.mapper;

import com.ynov.guilde.application.dto.AventurierRequestDto;
import com.ynov.guilde.application.dto.AventurierResponseDto;
import com.ynov.guilde.domain.model.Aventurier;
import org.springframework.stereotype.Component;

@Component
public class AventurierMapper {

    // Transforme la requête en objet métier (qui va s'auto-valider grâce à son constructeur)
    public Aventurier toDomain(AventurierRequestDto dto) {
        return new Aventurier(
            dto.nom(),
            dto.description(),
            dto.physique(),
            dto.mental(),
            dto.perception(),
            dto.classe()
        );
    }

    // Transforme l'objet métier en réponse pour le client
    public AventurierResponseDto toDto(Aventurier aventurier) {
        return new AventurierResponseDto(
            aventurier.getId(),
            aventurier.getNom(),
            aventurier.getDescription(),
            aventurier.getPhysique(),
            aventurier.getMental(),
            aventurier.getPerception(),
            aventurier.getNiveau(),
            aventurier.getClasse()
        );
    }
}