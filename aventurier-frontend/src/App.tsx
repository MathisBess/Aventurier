// src/App.tsx
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Accueil } from './pages/Accueil';
import { AventurierDetail } from './pages/AventurierDetail';

export const App: React.FC = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Accueil />} />
        <Route path="/aventuriers/:id" element={<AventurierDetail />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;