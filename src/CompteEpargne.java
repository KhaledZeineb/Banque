public class CompteEpargne extends CompteClient {
    private double benefice;

    // Constructeur
    public CompteEpargne(String numeroCompte, double solde, double tauxInteret) {
        super(numeroCompte, solde, tauxInteret);
        this.benefice = 0;
    }

    // Calcul spécifique du bénéfice pour un compte épargne
    @Override
    public void calculerBenefice() {
        this.benefice = getSolde() * 0.05;  // Bénéfice spécifique pour un compte épargne
        depot(benefice);  // Ajouter le bénéfice sur le compte
        System.out.println("Bénéfice de " + benefice + " ajouté sur le compte épargne.");
    }
}
