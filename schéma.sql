CREATE TABLE banque (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL
);

CREATE TABLE compte (
                        id SERIAL PRIMARY KEY,
                        numero_compte VARCHAR(50) UNIQUE NOT NULL,
                        solde DOUBLE PRECISION NOT NULL CHECK (solde >= 0),
                        taux_interet DOUBLE PRECISION CHECK (taux_interet >= 0), -- Peut être NULL pour un compte courant
                        type_compte VARCHAR(50) NOT NULL CHECK (type_compte IN ('courant', 'epargne')),
                        banque_id INT REFERENCES banque(id) ON DELETE CASCADE,
                        CONSTRAINT chk_type_compte CHECK (
                            (type_compte = 'courant' AND taux_interet IS NULL) OR
                            (type_compte = 'epargne' AND taux_interet IS NOT NULL)
                            )
);
INSERT INTO compte (numero_compte, solde, taux_interet, type_compte, banque_id)
VALUES ('57895', 78520, NULL, 'courant', 1);
