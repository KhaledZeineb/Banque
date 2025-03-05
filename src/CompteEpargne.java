class CompteEpargne extends CompteClient {
    public CompteEpargne(String numeroCompte, double solde, double tauxInteret) {
        super(numeroCompte, solde, tauxInteret);
    }
    public void calculerBenefice() {
        double benefice = getSolde() * getTauxInteret() / 100;
        depot(benefice);
        System.out.println("Bénéfice de " + benefice + " ajouté.");
    }
}