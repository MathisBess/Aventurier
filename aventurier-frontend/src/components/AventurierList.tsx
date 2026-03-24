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
            <div className={styles.noData} style={{ fontSize: '1.2rem', padding: '3rem' }}>
                🌊 Aucun pirate en vue sur cette mer. La paix règne sur Grand Line... pour l'instant.
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