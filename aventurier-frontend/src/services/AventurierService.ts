import type { Aventurier, AventurierRequest } from '../types/Aventurier';

const API_URL = 'http://localhost:8080/api/aventuriers';

export const AventurierService = {
    listerTous: async (): Promise<Aventurier[]> => {
        try {
            const response = await fetch(API_URL);
            
            if (!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }
            
            return await response.json();
        } catch (error) {
            console.error("Erreur lors de la récupération des aventuriers:", error);
            throw new Error("Impossible de contacter le serveur d'aventuriers.");
        }
    },

    creer: async (nouvelAventurier: AventurierRequest): Promise<Aventurier> => {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nouvelAventurier)
        });
        
        if (!response.ok) {
            const errorData = await response.json().catch(() => null);
            
            if (errorData && errorData.erreurs && Array.isArray(errorData.erreurs)) {
                throw new Error("Validation échouée : " + errorData.erreurs.join(" | "));
            }
            
            throw new Error(errorData?.detail || errorData?.title || `Erreur HTTP: ${response.status}`);
        }
        
        return await response.json();
    },

    consulter: async (id: string): Promise<Aventurier> => {
        const response = await fetch(`${API_URL}/${id}`);
        
        if (!response.ok) {
            const errorData = await response.json().catch(() => null);
            throw new Error(errorData?.detail || `Erreur HTTP: ${response.status} - Aventurier introuvable`);
        }
        
        return await response.json();
    }
};