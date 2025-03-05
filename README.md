# Banque - Gestion de Comptes

Ce projet implémente un système de gestion de comptes bancaires avec différentes fonctionnalités telles que l'ajout d'une banque, l'ajout de comptes clients, la mise à jour des comptes et la gestion des dépôts et retraits. Il contient également des fonctionnalités spécifiques pour les comptes d'épargne, y compris le calcul des bénéfices basés sur le taux d'intérêt.

## Fonctionnalités

1. **Gestion de banque**
   - Ajouter une banque.
   - Gérer les comptes liés à chaque banque.
   
2. **Gestion de comptes**
   - Ajouter un compte bancaire (Courant ou Épargne).
   - Lire les informations d'un compte bancaire.
   - Mettre à jour les informations d'un compte bancaire.
   
3. **Comptes d'épargne**
   - Calculer les bénéfices basés sur un taux d'intérêt pour un compte d'épargne.

4. **Gestion des transactions**
   - Déposer de l'argent sur un compte.
   - Retirer de l'argent d'un compte (avec gestion des fonds insuffisants).

## Structure de la Base de Données

La base de données utilise PostgreSQL avec deux tables principales :

- **banque** : Table contenant les informations sur les banques.
- **compte** : Table contenant les informations sur les comptes bancaires, avec des vérifications des types de comptes et des contraintes de solde.

```sql
CREATE TABLE banque (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE compte (
    id SERIAL PRIMARY KEY,
    numero_compte VARCHAR(50) UNIQUE NOT NULL,
    solde DOUBLE PRECISION NOT NULL CHECK (solde >= 0),
    taux_interet DOUBLE PRECISION CHECK (taux_interet >= 0),
    type_compte VARCHAR(50) NOT NULL CHECK (type_compte IN ('courant', 'epargne')),
    banque_id INT REFERENCES banque(id) ON DELETE CASCADE,
    CONSTRAINT chk_type_compte CHECK (
        (type_compte = 'courant' AND taux_interet IS NULL) OR
        (type_compte = 'epargne' AND taux_interet IS NOT NULL)
    )
);
```

## Exemple d'insertion dans la table compte

```sql
INSERT INTO compte (numero_compte, solde, taux_interet, type_compte, banque_id)
VALUES ('57895', 78520, NULL, 'courant', 1);
```

## Classes et Description

1. **Test.java** : Point d'entrée du programme. Permet de simuler les ajouts de banques et de comptes, ainsi que les opérations de dépôt et de retrait.
2. **CompteClient.java** : Classe représentant un compte bancaire générique. Permet de gérer les opérations de dépôt, retrait et de récupérer les informations du compte.
3. **CompteEpargne.java** : Hérite de CompteClient. Permet de calculer et d'ajouter les bénéfices pour un compte d'épargne basé sur le taux d'intérêt.
4. **SoldeInsuffisantException.java** : Exception personnalisée pour gérer les erreurs liées aux soldes insuffisants lors des opérations de retrait.
5. **Bank.java** : Classe représentant une banque qui gère les comptes bancaires et les banques enregistrées dans une base de données PostgreSQL. Elle permet de se connecter à la base de données, d'ajouter une nouvelle banque, d'ajouter un compte bancaire associé à une banque, de récupérer les informations d’un compte, de les mettre à jour et de supprimer un compte bancaire.

## Utilisation

Étapes de l'exécution :
- Ajouter une banque (par exemple, "Banque A").
- Ajouter un compte d'épargne (avec un solde initial et un taux d'intérêt).
- Lire et afficher les informations du compte avant modification.
- Ajouter un dépôt sur le compte.
- Lire et afficher les informations du compte après modification.

### Exemple de sortie :

```
Compte avant mise à jour: Solde = 80000.0
Dépôt de 900.0 effectué. Solde: 80900.0
Compte après mise à jour: Solde = 80900.0
```

## Prérequis

- Java 8 ou version supérieure
- PostgreSQL pour la gestion de la base de données
- JDBC pour la connexion à la base de données

## Installation

1. Clonez ce repository sur votre machine locale :

   ```sh
   git clone https://github.com/KhaledZeineb/Banque.git
   ```

2. Configurez votre base de données PostgreSQL et créez les tables décrites dans la section "Structure de la Base de Données".
3. Modifiez les configurations de connexion à la base de données dans le code, si nécessaire.
4. Compilez et exécutez le programme Java :

   ```sh
   javac Test.java
   java Test
   ```

## Contributions

Les contributions sont les bienvenues ! Si vous avez des idées pour améliorer ce projet, veuillez créer une issue ou soumettre une pull request.

Merci d'utiliser ce projet pour gérer vos comptes bancaires ! 🚀

