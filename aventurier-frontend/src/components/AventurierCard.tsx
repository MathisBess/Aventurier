import React from 'react';
import { Link } from 'react-router-dom';
import type { Aventurier } from '../types/Aventurier';
import styles from './AventurierCard.module.css';

interface AventurierCardProps {
    aventurier: Aventurier;
}

export const AventurierCard: React.FC<AventurierCardProps> = ({ aventurier }) => {
    const prime = (aventurier.niveau * 15000000) + (aventurier.physique * 500000);

    return (
        <Link to={`/aventuriers/${aventurier.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
            <article className={styles.card}>
                <div className={styles.wantedTop}>
                    <span className={styles.marineText}>MARINE ISSUE</span>
                    <h3 className={styles.deadOrAlive}>WANTED<br/>DEAD OR ALIVE</h3>
                </div>
                
                <header className={styles.header}>
                    <h2 className={styles.nom}>{aventurier.nom}</h2>
                </header>
                
                <div className={styles.primeContainer}>
                    <span className={styles.currency}>฿</span>
                    <span className={styles.primeValue}>{prime.toLocaleString('en-US')}</span>
                    <span className={styles.currency}>-</span>
                </div>
                
                <div className={styles.classe}>
                    Rôle : {aventurier.classe.replace(/_/g, ' ')}
                </div>
            </article>
        </Link>
    );
};