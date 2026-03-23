package com.ynov.guilde.infrastructure.web;

import java.util.List;
import java.util.UUID;
import com.ynov.guilde.application.dto.AventurierResponseDto;
import com.ynov.guilde.application.dto.AventurierRequestDto;
import com.ynov.guilde.application.usecase.ConsulterAventurierUseCase;
import com.ynov.guilde.application.usecase.CreerAventurierUseCase;
import com.ynov.guilde.application.usecase.FaireMonterNiveauUseCase;
import com.ynov.guilde.application.usecase.ListerAventuriersUseCase;
import com.ynov.guilde.application.usecase.ModifierAventurierUseCase;
import com.ynov.guilde.application.usecase.SupprimerAventurierUseCase;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aventuriers")
@CrossOrigin(origins = "http://localhost:5173")
public class AventurierController {

    private final CreerAventurierUseCase creerAventurierUseCase;
    private final ListerAventuriersUseCase listerAventuriersUseCase;
    private final ConsulterAventurierUseCase consulterAventurierUseCase;
    private final ModifierAventurierUseCase modifierAventurierUseCase;
    private final FaireMonterNiveauUseCase faireMonterNiveauUseCase;
    private final SupprimerAventurierUseCase supprimerAventurierUseCase;

    public AventurierController(CreerAventurierUseCase creerAventurierUseCase, 
                                ListerAventuriersUseCase listerAventuriersUseCase, 
                                ConsulterAventurierUseCase consulterAventurierUseCase,
                                ModifierAventurierUseCase modifierAventurierUseCase,
                                FaireMonterNiveauUseCase faireMonterNiveauUseCase,
                                SupprimerAventurierUseCase supprimerAventurierUseCase) {
        this.creerAventurierUseCase = creerAventurierUseCase;
        this.listerAventuriersUseCase = listerAventuriersUseCase;
        this.consulterAventurierUseCase = consulterAventurierUseCase;
        this.modifierAventurierUseCase = modifierAventurierUseCase;
        this.faireMonterNiveauUseCase = faireMonterNiveauUseCase;
        this.supprimerAventurierUseCase = supprimerAventurierUseCase;
    }

    @PostMapping
    public ResponseEntity<AventurierResponseDto> creerAventurier(@Valid @RequestBody AventurierRequestDto requete) {
        
        AventurierResponseDto reponse = creerAventurierUseCase.executer(requete);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }

    @GetMapping
    public ResponseEntity<List<AventurierResponseDto>> listerAventuriers() {
        return ResponseEntity.ok(listerAventuriersUseCase.executer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AventurierResponseDto> consulterAventurier(@PathVariable UUID id) {
        return ResponseEntity.ok(consulterAventurierUseCase.executer(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AventurierResponseDto> modifierAventurier(@PathVariable UUID id, @Valid @RequestBody AventurierRequestDto requete) {
        return ResponseEntity.ok(modifierAventurierUseCase.executer(id, requete));
    }

    @PatchMapping("/{id}/niveau")
    public ResponseEntity<AventurierResponseDto> faireMonterNiveau(@PathVariable UUID id) {
        return ResponseEntity.ok(faireMonterNiveauUseCase.executer(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAventurier(@PathVariable UUID id) {
        supprimerAventurierUseCase.executer(id);
        return ResponseEntity.noContent().build();
    }
}