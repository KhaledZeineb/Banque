public class Test {
    public static void main(String[] args) {
        Bank banque = new Bank();

        // 1️⃣ Ajouter une banque
        banque.ajouterBanque("Banque A");

        // 2️⃣ Ajouter un compte
        CompteEpargne compte = new CompteEpargne("75287", 80000, 7.0);
        banque.ajouterCompte(compte, "epargne", 3);

        // 3️⃣ Lire et afficher le compte avant modification
        CompteClient compteAvantMaj = banque.lireCompte("75287");
        if (compteAvantMaj != null) {
            System.out.println("Compte avant mise à jour: Solde = " + compteAvantMaj.getSolde());
        }

        // 4️⃣ Modifier le compte (ajouter un dépôt)
        compte.depot(900);
        banque.mettreAJourCompte(compte);

        // 5️⃣ Lire et afficher le compte après modification
        CompteClient compteApresMaj = banque.lireCompte("75287");
        if (compteApresMaj != null) {
            System.out.println("Compte après mise à jour: Solde = " + compteApresMaj.getSolde());
        }


    }
}
