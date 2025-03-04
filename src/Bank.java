import java.sql.*;

class Bank {
    private Connection connection;

    // Constructeur pour établir la connexion à la base de données
    public Bank() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banque 1", "postgres", "123456789");
            System.out.println("Connexion réussie à la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ajouter un compte
    public void ajouterCompte(CompteClient compte, String type) {
        try {
            String query = "INSERT INTO compte (numero_compte, solde, taux_interet, type_compte) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, compte.getNumeroCompte());
            stmt.setDouble(2, compte.getSolde());
            stmt.setDouble(3, compte.getTauxInteret());
            stmt.setString(4, type);
            stmt.executeUpdate();  // Exécuter l'insertion
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();  // Fermer la connexion
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Lire un compte par numéro de compte
    public CompteClient lireCompte(String numeroCompte) {
        CompteClient compte = null;
        try {
            String query = "SELECT * FROM compte WHERE numero_compte = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, numeroCompte);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                compte = new CompteClient(
                        rs.getString("numero_compte"),
                        rs.getDouble("solde"),
                        rs.getDouble("taux_interet")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();  // Fermer la connexion
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return compte;
    }

    // Mettre à jour un compte
    public void mettreAJourCompte(CompteClient compte) {
        try {
            String query = "UPDATE compte SET solde = ?, taux_interet = ? WHERE numero_compte = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, compte.getSolde());
            stmt.setDouble(2, compte.getTauxInteret());
            stmt.setString(3, compte.getNumeroCompte());
            stmt.executeUpdate();  // Exécuter la mise à jour
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();  // Fermer la connexion
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Supprimer un compte
    public void supprimerCompte(String numeroCompte) {
        try {
            String query = "DELETE FROM compte WHERE numero_compte = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, numeroCompte);
            stmt.executeUpdate();  // Exécuter la suppression
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();  // Fermer la connexion
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
