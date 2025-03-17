package baseDonnees;

import baseDonnees.modeles.Transaction;
import baseDonnees.modeles.Utilisateur;

import java.util.ArrayList;
import java.util.List;


public class TableTransactions {

    private List<Transaction> transactions;

    public TableTransactions() {
        transactions = new ArrayList<>();
    }

    public void ajouterUneTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> obtenirTransactionsPourCompte(String numeroDeCompte) {
        List<Transaction> transactionsPourCompte = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getNoCompteSource().equals(numeroDeCompte) ||
                    transaction.getNoCompteDestination().equals(numeroDeCompte)) {
                transactionsPourCompte.add(transaction);
            }
        }

        return transactionsPourCompte;
    }
}