
export interface Aventurier {
    id: string;
    nom: string;
    description: string;
    physique: number;
    mental: number;
    perception: number;
    niveau: number;
    classe: Classe;
}

export interface AventurierRequest {
    nom: string;
    description: string;
    physique: number;
    mental: number;
    perception: number;
    classe: Classe;
}

export type Classe = 'mage' | 'tireur' | 'tank' | 'soigneur' | 'guerrier' | 'assassin';