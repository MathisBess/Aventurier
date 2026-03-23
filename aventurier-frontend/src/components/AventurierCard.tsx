import React from 'react';
import type { Aventurier } from '../types/Aventurier';
import styles from './AventurierCard.module.css';
import { Link } from 'react-router-dom';

interface AventurierCardProps {
    aventurier: Aventurier;
}

export const AventurierCard: React.FC<AventurierCardProps> = ({ aventurier }) => {
    return (
        <Link to={`/aventuriers/${aventurier.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
            <article className={styles.card}>
                <header className={styles.header}>
                    <h2 className={styles.nom}>{aventurier.nom}</h2>
                    <span className={styles.niveau}>Niv. {aventurier.niveau}</span>
                </header>
                
                <div className={styles.classe}>
                    Classe : {aventurier.classe.replace(/_/g, ' ')}
                </div>
                
                <p className={styles.description}>
                    {aventurier.description || "Aucune description fournie."}
                </p>
            </article>
        </Link>
    );
};