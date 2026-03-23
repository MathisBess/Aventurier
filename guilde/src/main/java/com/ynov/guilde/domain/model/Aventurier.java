package com.ynov.guilde.domain.model;

import com.ynov.guilde.domain.exception.NomInvalideException;
import java.util.UUID;

public class Aventurier {
    private UUID id;
    private String nom;
    private String description;
    private int physique;
    private int mental;
    private int perception;
    private int niveau;
    private Classe classe;

    public Aventurier(String nom, String description, int physique, int mental, int perception, Classe classe) {
        if (nom == null || nom.isBlank()) {
            throw new NomInvalideException(); 
        }
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.description = description;
        this.physique = physique;
        this.mental = mental;
        this.perception = perception;
        this.classe = classe;
        this.niveau = 1; 
    }

    public void monterDeNiveau() {
        this.niveau++;
    }

    public void mettreAJourProfil(String description, int physique, int mental, int perception) {
        this.description = description;
        this.physique = physique;
        this.mental = mental;
        this.perception = perception;
    }

    public UUID getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public int getPhysique() { return physique; }
    public int getMental() { return mental; }
    public int getPerception() { return perception; }
    public int getNiveau() { return niveau; }
    public Classe getClasse() { return classe; }

    public Aventurier(UUID id, String nom, String description, int physique, int mental, int perception, int niveau, Classe classe) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.physique = physique;
        this.mental = mental;
        this.perception = perception;
        this.niveau = niveau;
        this.classe = classe;
    }
}