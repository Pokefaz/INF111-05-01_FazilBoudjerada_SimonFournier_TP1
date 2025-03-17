package baseDonnees.bases;

import baseDonnees.TableTransactions;
import baseDonnees.TableUtilisateurs;
import baseDonnees.modeles.Transaction;
import baseDonnees.modeles.Utilisateur;
import java.util.List;

public class BaseDonnees {

    private TableUtilisateurs tableUtilisateurs;
    private TableTransactions tableTransactions;

    public BaseDonnees() {
        this.tableUtilisateurs = new TableUtilisateurs();
        this.tableTransactions = new TableTransactions();
    }

    public void viderLaBd() {
        this.tableUtilisateurs = new TableUtilisateurs();
        this.tableTransactions = new TableTransactions();
    }

    public boolean ajouterUtilisateur(Utilisateur nouvelUtilisateur) {
        return tableUtilisateurs.ajouterUnUtilisateur(nouvelUtilisateur);
    }

    public Utilisateur obtenirUtilisateurParNom(String nomUtilisateur) {
        return tableUtilisateurs.obtenirUtilisateurParNom(nomUtilisateur);
    }

    public Utilisateur obtenirUtilisateurPourCompte(String numeroDeCompte) {
        return tableUtilisateurs.obtenirUtilisateurParCompte(numeroDeCompte);
    }

    public void mettreAJourSoldeUtilisateur(Utilisateur utilisateur, double solde) {
        tableUtilisateurs.mettreAJourSoldeUtilisateur(utilisateur, solde);
    }

    public void ajouterTransaction(Transaction transaction) {
        tableTransactions.ajouterUneTransaction(transaction);
    }

    public List<Transaction> obtenirTransactionsPourCompte(String numeroDeCompte) {
        return tableTransactions.obtenirTransactionsPourCompte(numeroDeCompte);
    }

    public static void main(String[] args) {
        BaseDonnees base = new BaseDonnees();

        Utilisateur utilisateur1 = new Utilisateur("Bob", "password123", "1001", 1500.0);
        base.ajouterUtilisateur(utilisateur1);

        Utilisateur retrievedByName = base.obtenirUtilisateurParNom("Bob");
        System.out.println(retrievedByName);

        Transaction transaction1 = new Transaction("1001", "1002", 200.0, Transaction.ACCEPTE);
        base.ajouterTransaction(transaction1);

        List<Transaction> transactions = base.obtenirTransactionsPourCompte("1001");
        System.out.println(transactions);

        base.mettreAJourSoldeUtilisateur(utilisateur1, 1700.0);
        System.out.println("Solde après mise à jour: " + utilisateur1.getSolde());
    }
}