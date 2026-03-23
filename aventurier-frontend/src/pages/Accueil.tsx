import React, { useState, useEffect } from 'react';
import type { Aventurier } from '../types/Aventurier';
import { AventurierService } from '../services/AventurierService';
import { AventurierList } from '../components/AventurierList';
import { AventurierForm } from '../components/AventurierForm';
import styles from '../App.module.css'; 

export const Accueil: React.FC = () => {
  const [aventuriers, setAventuriers] = useState<Aventurier[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const chargerDonnees = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await AventurierService.listerTous();
      setAventuriers(data);
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    chargerDonnees();
  }, []);

  return (
    <main className={styles.container}>
      <h1 className={styles.title}>La Guilde des Aventuriers</h1>

      <AventurierForm onAventurierCree={chargerDonnees} />

      {loading && <div className={styles.loader}>Chargement des aventuriers...</div>}
      
      {error && <div className={styles.error}>{error}</div>}
      
      {!loading && !error && <AventurierList aventuriers={aventuriers} />}
    </main>
  );
};