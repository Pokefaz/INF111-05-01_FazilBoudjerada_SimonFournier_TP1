package baseDonnees;

import baseDonnees.modeles.Utilisateur;

public class TableUtilisateurs {

    private ColonneIndexee<String> nomsUtilisateurs;
    private ColonneIndexee<String> numerosComptes;
    private Colonne<Double> soldes;
    private Colonne<byte[]> salts;
    private Colonne<byte[]> hashes;

    public TableUtilisateurs() {
        this.nomsUtilisateurs = new ColonneIndexee<>();
        this.numerosComptes = new ColonneIndexee<>();
        this.soldes = new Colonne<>();
        this.salts = new Colonne<>();
        this.hashes = new Colonne<>();
    }

    public boolean ajouterUnUtilisateur(Utilisateur utilisateur) {
        if (!nomsUtilisateurs.estUnique(utilisateur.getNomUtilisateur()) ||
                !numerosComptes.estUnique(utilisateur.getNumeroDeCompte())) {
            return false;
        }

        nomsUtilisateurs.ajouterValeur(utilisateur.getNomUtilisateur());
        numerosComptes.ajouterValeur(utilisateur.getNumeroDeCompte());
        soldes.ajouterValeur(utilisateur.getSolde());
        salts.ajouterValeur(utilisateur.getSalt());
        hashes.ajouterValeur(utilisateur.getHashPassword());
        return true;
    }

    public Utilisateur obtenirUtilisateurParNom(String nomUtilisateur) {
        try {
            int index = nomsUtilisateurs.obtenirIndex(nomUtilisateur);
            return new Utilisateur(
                    nomsUtilisateurs.obtenirValeur(index),
                    hashes.obtenirValeur(index),
                    salts.obtenirValeur(index),
                    numerosComptes.obtenirValeur(index),
                    soldes.obtenirValeur(index)
            );
        } catch (Exception e) {
            return null;
        }
    }

    public Utilisateur obtenirUtilisateurParCompte(String numeroDeCompte) {
        try {
            int index = numerosComptes.obtenirIndex(numeroDeCompte);
            return new Utilisateur(
                    nomsUtilisateurs.obtenirValeur(index),
                    hashes.obtenirValeur(index),
                    salts.obtenirValeur(index),
                    numerosComptes.obtenirValeur(index),
                    soldes.obtenirValeur(index)
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void mettreAJourSoldeUtilisateur(Utilisateur utilisateur, double nouveauSolde) {
        try {
            int index = numerosComptes.obtenirIndex(utilisateur.getNumeroDeCompte());
            soldes.changerValeur(index, nouveauSolde);
        } catch (Exception e) {
            System.out.println("Utilisateur non trouvé pour la mise à jour du solde.");
        }
    }


    // Validation

    public static void main(String[] args) {
        TableUtilisateurs table = new TableUtilisateurs();

        // Test ajout utilisateur
        Utilisateur user1 = new Utilisateur("Bob", "password123", "12345", 1000.0);
        assert table.ajouterUnUtilisateur(user1) : "Ajout de l'utilisateur échoué.";

        // Test trouver utilisateur par nom
        Utilisateur fetchedByName = table.obtenirUtilisateurParNom("Bob");
        assert fetchedByName != null : "Utilisateur non trouvé par nom.";
        assert fetchedByName.getNumeroDeCompte().equals("12345") : "Numéro de compte incorrect.";

        // Test trouver utilisateur par numéro de compte
        Utilisateur fetchedByAccount = table.obtenirUtilisateurParCompte("12345");
        assert fetchedByAccount != null : "Utilisateur non trouvé par compte.";
        assert fetchedByAccount.getNomUtilisateur().equals("Bob") : "Nom d'utilisateur incorrect.";

        // Test mise à jour du solde
        table.mettreAJourSoldeUtilisateur(user1, 2000.0);
        Utilisateur updatedUser = table.obtenirUtilisateurParCompte("12345");
        assert updatedUser.getSolde() == 2000.0 : "Mise à jour du solde échouée.";

        System.out.println("Tests réussis");
    }
}
