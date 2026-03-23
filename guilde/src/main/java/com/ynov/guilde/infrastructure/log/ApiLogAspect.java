package com.ynov.guilde.infrastructure.log;

import com.ynov.guilde.domain.exception.NomInvalideException;
import com.ynov.guilde.domain.exception.AventurierNonTrouveException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

//@Aspect
//@Component
public class ApiLogAspect {

    private final ApiLogMongoRepository logRepository;
    private static final Logger logger = LoggerFactory.getLogger(ApiLogAspect.class);

    public ApiLogAspect(ApiLogMongoRepository logRepository) {
        this.logRepository = logRepository;
    }

    // Cette ligne cible TOUTES les méthodes de ton AventurierController
    @Around("execution(* com.ynov.guilde.infrastructure.web.AventurierController.*(..))")
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        
        String methode = request.getMethod();
        String endpoint = request.getRequestURI();
        String parametres = Arrays.toString(joinPoint.getArgs()); // Les DTOs ou IDs reçus
        
        try {
            // On laisse la requête s'exécuter normalement vers le Controller
            Object resultat = joinPoint.proceed();
            
            // Consigne : "S'il n'y a pas de sortie, logger le succès."
            String donneesSortie = (resultat != null) ? resultat.toString() : "Succès (Aucune donnée de sortie)";
            
            // On sauvegarde en base Mongo
            ApiLog log = new ApiLog(methode, endpoint, parametres, donneesSortie, "INFO");
            logRepository.save(log);
            
            return resultat;

        } catch (Exception ex) {
            // Consigne : "Logger les erreurs client en warn et les erreurs du serveur en error"
            String niveauLog = "ERROR"; // Par défaut, erreur serveur (500)
            
            // Si c'est une erreur de validation ou métier (400, 404), c'est une erreur "Client" (WARN)
            if (ex instanceof NomInvalideException || 
                ex instanceof AventurierNonTrouveException || 
                ex instanceof MethodArgumentNotValidException) {
                niveauLog = "WARN";
                logger.warn("Erreur Client sur {}: {}", endpoint, ex.getMessage());
            } else {
                logger.error("Erreur Serveur sur {}: {}", endpoint, ex.getMessage());
            }

            ApiLog log = new ApiLog(methode, endpoint, parametres, "ERREUR : " + ex.getMessage(), niveauLog);
            logRepository.save(log);
            
            throw ex; // On relance l'erreur pour que ton ApiExceptionHandler l'attrape comme d'habitude !
        }
    }
}