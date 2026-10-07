-- Migration de base : sert à initialiser l'historique Flyway.
-- Les tables métier (utilisateurs, ligues, pronostics...) arriveront dans les migrations suivantes (V2, V3...).
-- Règle d'or : une migration déjà appliquée ne se modifie JAMAIS. On en ajoute une nouvelle.
SELECT 1;
