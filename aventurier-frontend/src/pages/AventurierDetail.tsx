import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import type { Aventurier } from '../types/Aventurier';
import { AventurierService } from '../services/AventurierService';

export const AventurierDetail: React.FC = () => {
    const { id } = useParams<{ id: string }>(); 
    const [aventurier, setAventurier] = useState<Aventurier | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const chargerDetail = async () => {
            if (!id) return;
            
            try {
                setLoading(true);
                setError(null);
                const data = await AventurierService.consulter(id);
                setAventurier(data);
            } catch (err: any) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        chargerDetail();
    }, [id]); 

    if (loading) return <div style={{ textAlign: 'center', padding: '2rem' }}>Chargement du profil...</div>;
    if (error) return <div style={{ textAlign: 'center', padding: '2rem', color: '#dc2626' }}>{error}</div>;
    if (!aventurier) return <div style={{ textAlign: 'center', padding: '2rem' }}>Aventurier introuvable.</div>;

    return (
        <div style={{ maxWidth: '800px', margin: '0 auto', padding: '2rem' }}>
            <Link to="/" style={{ color: '#4f46e5', textDecoration: 'none', fontWeight: 'bold' }}>
                ← Retour à la guilde
            </Link>
            
            <h1 style={{ fontSize: '3rem', marginBottom: '0.5rem' }}>{aventurier.nom}</h1>
            <h2 style={{ color: '#6b7280', marginTop: 0, textTransform: 'capitalize' }}>
                Niveau {aventurier.niveau} - {aventurier.classe.replace(/_/g, ' ')}
            </h2>
            
            <div style={{ backgroundColor: 'white', padding: '2rem', borderRadius: '8px', border: '1px solid #e5e7eb', marginTop: '2rem' }}>
                <h3>Description</h3>
                <p>{aventurier.description || "Aucune description renseignée par cet aventurier."}</p>
                
                <h3>Statistiques</h3>
                <ul style={{ listStyleType: 'none', padding: 0, lineHeight: '2', fontSize: '1.1rem' }}>
                    <li>💪 Physique : {aventurier.physique}</li>
                    <li>🧠 Mental : {aventurier.mental}</li>
                    <li>👁️ Perception : {aventurier.perception}</li>
                </ul>
            </div>
        </div>
    );
};