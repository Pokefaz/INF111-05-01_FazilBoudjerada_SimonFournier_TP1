package baseDonnees.modeles;

public class Transaction {

    public static final String ACCEPTE = "Accepte";
    public static final String REFUSE = "Refuse";
    public static final String A_DETERMINER = "A determiner";

    private String noCompteSource;
    private String noCompteDestination;
    private double montant;
    private String statut;

    public Transaction(String noCompteSource, String noCompteDestination, double montant, String statut) {
        this.noCompteSource = noCompteSource;
        this.noCompteDestination = noCompteDestination;
        this.montant = montant;
        this.statut = statut;
    }

    public Transaction(String noCompteSource, String noCompteDestination, double montant) {
        this.noCompteSource = noCompteSource;
        this.noCompteDestination = noCompteDestination;
        this.montant = montant;
        this.statut = A_DETERMINER;
    }

    public String getNoCompteSource() {
        return noCompteSource;
    }

    public String getNoCompteDestination() {
        return noCompteDestination;
    }

    public double getMontant() {
        return montant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}