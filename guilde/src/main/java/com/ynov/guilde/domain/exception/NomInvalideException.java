package com.ynov.guilde.domain.exception;

public class NomInvalideException extends RuntimeException {
    public NomInvalideException() {
        super("Le nom de l'aventurier ne respecte pas les règles métier");
    }
}