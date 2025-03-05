import java.sql.*;

class Bank {
    private Connection connection;

    public Bank() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banque 1", "postgres", "123456789");
            System.out.println("Connexion réussie.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterBanque(String nom) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO banque (nom) VALUES (?)")) {
            stmt.setString(1, nom);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterCompte(CompteClient compte, String type, int banqueId) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO compte (numero_compte, solde, taux_interet, type_compte, banque_id) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, compte.getNumeroCompte());
            stmt.setDouble(2, compte.getSolde());
            stmt.setDouble(3, compte.getTauxInteret());
            stmt.setString(4, type);
            stmt.setInt(5, banqueId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CompteClient lireCompte(String numeroCompte) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM compte WHERE numero_compte = ?")) {
            stmt.setString(1, numeroCompte);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CompteClient(rs.getString("numero_compte"), rs.getDouble("solde"), rs.getDouble("taux_interet"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void mettreAJourCompte(CompteClient compte) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE compte SET solde = ?, taux_interet = ? WHERE numero_compte = ?")) {
            stmt.setDouble(1, compte.getSolde());
            stmt.setDouble(2, compte.getTauxInteret());
            stmt.setString(3, compte.getNumeroCompte());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerCompte(String numeroCompte) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM compte WHERE numero_compte = ?")) {
            stmt.setString(1, numeroCompte);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}