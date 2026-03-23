import React from 'react';
import type { Aventurier } from '../types/Aventurier';
import { AventurierCard } from './AventurierCard';
import styles from './AventurierList.module.css';

interface AventurierListProps {
    aventuriers: Aventurier[];
}

export const AventurierList: React.FC<AventurierListProps> = ({ aventuriers }) => {
    if (aventuriers.length === 0) {
        return (
            <div className={styles.noData}>
                La guilde est vide. Aucun aventurier n'a été trouvé.
            </div>
        );
    }

    return (
        <div className={styles.grid}>
            {aventuriers.map((aventurier) => (
                <AventurierCard key={aventurier.id} aventurier={aventurier} />
            ))}
        </div>
    );
};