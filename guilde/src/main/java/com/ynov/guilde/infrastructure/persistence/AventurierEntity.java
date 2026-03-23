package com.ynov.guilde.infrastructure.persistence;

import com.ynov.guilde.domain.model.Classe;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "aventuriers")
public class AventurierEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 120)
    private String nom;

    @Column(length = 500)
    private String description;

    private int physique;
    private int mental;
    private int perception;
    
    @Column(nullable = false)
    private int niveau;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Classe classe;

    protected AventurierEntity() {}

    public AventurierEntity(UUID id, String nom, String description, int physique, int mental, int perception, int niveau, Classe classe) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.physique = physique;
        this.mental = mental;
        this.perception = perception;
        this.niveau = niveau;
        this.classe = classe;
    }

    public UUID getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public int getPhysique() { return physique; }
    public int getMental() { return mental; }
    public int getPerception() { return perception; }
    public int getNiveau() { return niveau; }
    public Classe getClasse() { return classe; }
}