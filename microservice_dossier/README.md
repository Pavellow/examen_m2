# Service Dossier Médical

Ce microservice fait partie du système de gestion de cabinet médical. Il gère les dossiers médicaux partagés entre praticiens et patients.

## Fonctionnalités

aucune

## Stack Technique

- Java 11
- Spring Boot
- PostgreSQL

## Structure de la Base de Données

Trop complexe pour rien, comme indiqué dans le README du repo git

## API Endpoints

N/A

## Création de la BDD

Exécuter ceci :

```SQL
CREATE TABLE dossiers_medicaux (
    id SERIAL PRIMARY KEY,
    patient_id INTEGER NOT NULL,
    date_creation TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    derniere_mise_a_jour TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE consultations (
    id SERIAL PRIMARY KEY,
    dossier_id INTEGER NOT NULL REFERENCES dossiers_medicaux(id),
    praticien_id INTEGER NOT NULL,
    date_consultation TIMESTAMP WITH TIME ZONE NOT NULL,
    motif TEXT NOT NULL,
    observations TEXT,
    diagnostic TEXT,
    traitement TEXT
);

CREATE TABLE documents_medicaux (
    id SERIAL PRIMARY KEY,
    dossier_id INTEGER NOT NULL REFERENCES dossiers_medicaux(id),
    type_document VARCHAR(50) NOT NULL,
    nom_fichier VARCHAR(255) NOT NULL,
    date_ajout TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    praticien_id INTEGER NOT NULL,
    description TEXT,
    contenu_document BYTEA
);

CREATE TABLE antecedents (
    id SERIAL PRIMARY KEY,
    dossier_id INTEGER NOT NULL REFERENCES dossiers_medicaux(id),
    type_antecedent type_antecedent NOT NULL,
    description TEXT NOT NULL,
    date_debut DATE,
    date_fin DATE,
    CONSTRAINT dates_coherentes CHECK (date_debut <= date_fin)
);

CREATE INDEX idx_dossier_patient ON dossiers_medicaux(patient_id);
CREATE INDEX idx_consultation_dossier ON consultations(dossier_id);
CREATE INDEX idx_documents_dossier ON documents_medicaux(dossier_id);
CREATE INDEX idx_antecedents_dossier ON antecedents(dossier_id);

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_dossier_updated_at
    BEFORE UPDATE ON dossiers_medicaux
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();
```
