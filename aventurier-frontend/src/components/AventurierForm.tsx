import React, { useState } from 'react';
import type { AventurierRequest, Classe } from '../types/Aventurier';
import { AventurierService } from '../services/AventurierService';
import styles from './AventurierForm.module.css';

interface AventurierFormProps {
    onAventurierCree: () => void; 
}

export const AventurierForm: React.FC<AventurierFormProps> = ({ onAventurierCree }) => {
    const [formData, setFormData] = useState<AventurierRequest>({
        nom: '',
        description: 'Un nouvel aventurier prêt pour la quête !', // Mettons une description par défaut pour éviter un autre 400
        physique: 10,
        mental: 10,
        perception: 10,
        classe: 'guerrier' // <-- On met 'guerrier' par défaut
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault(); 
        setLoading(true);
        setError(null);

        try {
            await AventurierService.creer({
                ...formData,
                physique: Number(formData.physique),
                mental: Number(formData.mental),
                perception: Number(formData.perception)
            });
            
            setFormData({ nom: '', description: '', physique: 10, mental: 10, perception: 10, classe: 'guerrier' });
            onAventurierCree(); 
        } catch (err: any) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <form className={styles.form} onSubmit={handleSubmit}>
            <h3>Recruter un nouvel aventurier</h3>
            
            {error && <div className={styles.error}>{error}</div>}

            <div className={styles.formGroup}>
                <label className={styles.label} htmlFor="nom">Nom de l'aventurier *</label>
                <input className={styles.input} type="text" id="nom" name="nom" required 
                       value={formData.nom} onChange={handleChange} />
            </div>

            <div className={styles.formGroup}>
                <label className={styles.label} htmlFor="classe">Classe *</label>
                <select className={styles.select} id="classe" name="classe" required 
                        value={formData.classe} onChange={handleChange}>
                    <option value="guerrier">Guerrier</option>
                    <option value="mage">Mage</option>
                    <option value="tireur">Tireur</option>
                    <option value="tank">Tank</option>
                    <option value="soigneur">Soigneur</option>
                    <option value="assassin">Assassin</option>
                </select>
            </div>

            <div className={styles.formGroup}>
                <label className={styles.label} htmlFor="description">Description *</label>
                <textarea 
                    className={styles.textarea} 
                    id="description" 
                    name="description" 
                    required 
                    value={formData.description} 
                    onChange={handleChange} 
                    rows={3}
                />
            </div>

            <button className={styles.button} type="submit" disabled={loading}>
                {loading ? 'Recrutement en cours...' : 'Recruter'}
            </button>
        </form>
    );
};