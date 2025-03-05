class CompteClient {
    private String numeroCompte;
    private double solde;
    private double tauxInteret;

    public CompteClient(String numeroCompte, double solde, double tauxInteret) {
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.tauxInteret = tauxInteret;
    }

    public String getNumeroCompte() { return numeroCompte; }
    public double getSolde() { return solde; }
    public double getTauxInteret() { return tauxInteret; }

    public void retrait(double montant) {
        if (montant > 0 && montant <= solde) {
            solde -= montant;
            System.out.println("Retrait de " + montant + " effectué. Nouveau solde: " + solde);
        } else {
            System.out.println("Retrait impossible : fonds insuffisants ou montant invalide.");
        }
    }


    public void depot(double montant) {
        if (montant > 0) {
            solde += montant;
            System.out.println("Dépôt de " + montant + " effectué. Solde: " + solde);
        }
    }
}