public class Test {
    public static void main(String[] args) {
        // Création de la banque
        Bank banque = new Bank();

        // Création d'un compte épargne
        CompteEpargne compteEpargne = new CompteEpargne("58962", 10000, 3.0);

        // Ajouter le compte à la banque
        banque.ajouterCompte(compteEpargne, "Epargne");

        // Dépôt et calcul du bénéfice
        compteEpargne.depot(500);
        compteEpargne.calculerBenefice();
    }
}
