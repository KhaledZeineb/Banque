public class CompteClient {
    private final String numeroCompte;
    private double solde;
    private final double tauxInteret;

    // Constructeur
    public CompteClient(String numeroCompte, double solde, double tauxInteret) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.tauxInteret = tauxInteret;
    }

    // Dépôt sur le compte
    public void depot(double montant) {
        if (montant > 0) {
            solde += montant;
            System.out.println("Dépôt de " + montant + " effectué. Nouveau solde: " + solde);
        } else {
            System.out.println("Montant invalide pour dépôt.");
        }
    }

    // Retrait sur le compte
    public void retrait(double montant) throws SoldeInsuffisantException {
        if (montant > solde) {
            throw new SoldeInsuffisantException("Fonds insuffisants pour ce retrait.");
        }
        solde -= montant;
        System.out.println("Retrait de " + montant + " effectué. Nouveau solde: " + solde);
    }

    // Calcul du bénéfice (sur le solde)
    public void calculerBenefice() {
        double benefice = solde * tauxInteret / 100;
        solde += benefice;
        System.out.println("Bénéfice de " + benefice + " ajouté. Nouveau solde: " + solde);
    }

    // Getters
    public double getSolde() {
        return solde;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

}
