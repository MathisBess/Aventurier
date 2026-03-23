package com.ynov.guilde.domain.exception;

import java.util.UUID;

public class AventurierNonTrouveException extends RuntimeException {
    public AventurierNonTrouveException(UUID id) {
        super("L'aventurier avec l'ID " + id + " n'existe pas.");
    }
}