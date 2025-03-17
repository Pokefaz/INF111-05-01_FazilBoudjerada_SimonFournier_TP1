package baseDonnees;

import baseDonnees.modeles.Transaction;
import baseDonnees.modeles.Utilisateur;

import java.util.Vector;

public class BaseDonnee {
    private TableTransactions tableTransactions;
    private TableUtilisateurs tableUtilisateurs;

    public BaseDonnee(){
        viderLaBd();
    }

    public void viderLaBd(){
        tableTransactions = new TableTransactions();
        tableUtilisateurs = new TableUtilisateurs();
    }

    public void ajouterUtilisateur(Utilisateur nouveauUtilisateur){
        tableUtilisateurs.ajouterUnUtilisateur(nouveauUtilisateur);
    }

    public Utilisateur obtenirUtilisateurParNom(String nomUtilisateur){
        return tableUtilisateurs.obtenirUtilisateurParNom(nomUtilisateur);
    }

    public Utilisateur obtenirUtilisateurPourCompte(String numeroDeCompte){
        return tableUtilisateurs.obtenirUtilisateurParCompte(numeroDeCompte);
    }

    public void mettreAJourSoldeUtilisateur(Utilisateur utilisateur, double solde){
        tableUtilisateurs.mettreAJourSoldeUtilisateur(utilisateur, solde);
    }

    public void ajouterTransaction(Transaction transaction){
        tableTransactions.ajouterUneTransaction(transaction);
    }

    public Vector<Transaction> obtenirTransactionsPourCompte(String numeroDeCompte){
        return tableTransactions.obtenirTransactionsPourCompte(numeroDeCompte);
    }
}
