# examen_m2

pas le niveau nécessaire pour aller plus loin

### Patients
```SQL
CREATE TABLE patients (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_naissance DATE,
    email VARCHAR(100) UNIQUE,
    telephone VARCHAR(20),
    adresse TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_patient_email ON patients(email);
CREATE INDEX idx_patient_nom_prenom ON patients(nom, prenom);
```

### Praticiens
```SQL
CREATE TABLE praticiens (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    specialite VARCHAR(100),
    email VARCHAR(100),
    telephone VARCHAR(20),
    numero_identification VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_praticien_numero ON praticiens(numero_identification);
CREATE INDEX idx_praticien_specialite ON praticiens(specialite);
```

### rdv
```SQL
CREATE TYPE status_type AS ENUM ('PLANIFIE', 'CONFIRME', 'ANNULE', 'TERMINE');

CREATE TABLE rdv (
    id BIGSERIAL PRIMARY KEY,
    id_patient BIGINT NOT NULL,
    id_praticien BIGINT NOT NULL,
    rdv_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    calendrier_event_id VARCHAR(100),
    status status_type DEFAULT 'PLANIFIE',
    external_calendar_event_id VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_rdv_patient ON rdv(id_patient);
CREATE INDEX idx_rdv_praticien ON rdv(id_praticien);
CREATE INDEX idx_rdv_datetime ON rdv(rdv_date_time);
```

### Dossier
```SQL
CREATE TABLE dossiers_medicaux (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    date_creation TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    derniere_mise_a_jour TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_dossier_patient ON dossiers_medicaux(patient_id);

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_patient_timestamp
    BEFORE UPDATE ON patients
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_praticien_timestamp
    BEFORE UPDATE ON praticiens
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_rdv_timestamp
    BEFORE UPDATE ON rdv
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_dossier_timestamp
    BEFORE UPDATE ON dossiers_medicaux
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
```
