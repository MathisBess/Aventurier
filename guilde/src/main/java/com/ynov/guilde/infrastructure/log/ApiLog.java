package com.ynov.guilde.infrastructure.log;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "api_logs")
public class ApiLog {
    @Id
    private String id;
    private LocalDateTime dateHeure;
    private String methode;
    private String endpoint;
    private String parametresEntree;
    private String donneesSortie;
    private String niveauLog;

    public ApiLog(String methode, String endpoint, String parametresEntree, String donneesSortie, String niveauLog) {
        this.dateHeure = LocalDateTime.now();
        this.methode = methode;
        this.endpoint = endpoint;
        this.parametresEntree = parametresEntree;
        this.donneesSortie = donneesSortie;
        this.niveauLog = niveauLog;
    }

    public String getId() { return id; }
    public LocalDateTime getDateHeure() { return dateHeure; }
    public String getMethode() { return methode; }
    public String getEndpoint() { return endpoint; }
    public String getParametresEntree() { return parametresEntree; }
    public String getDonneesSortie() { return donneesSortie; }
    public String getNiveauLog() { return niveauLog; }
}