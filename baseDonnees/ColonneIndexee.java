package baseDonnees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColonneIndexee<V extends Comparable<V>> extends Colonne<V> {

    private final List<ValeurIndexee> valeursIndexees;

    public ColonneIndexee() {
        valeursIndexees = new ArrayList<>();
    }
    
    @Override
    public void ajouterValeur(V valeur) {
        super.ajouterValeur(valeur);
        int index = getNbElements() - 1;
        ValeurIndexee valIndexee = new ValeurIndexee(valeur, index);
        insererValeurIndexee(valIndexee);
    }
    
    @Override
    public int obtenirIndex(V valeur) {
        int indexValeurIndexee = rechercheDichotomique(valeur);
        if (indexValeurIndexee == -1) {
            return -1;
        }
        return valeursIndexees.get(indexValeurIndexee).index;
    }
    
    public boolean estUnique(V valeur) {
        return rechercheDichotomique(valeur) == -1;
    }
    
    @Override
    public void changerValeur(int index, V valeur) {
        throw new IndexOutOfBoundsException("ERREUR : cette méthode n'est " +
                "pas supportée pour la classe colonneIndexee");
    }
    
    @Override
    public void afficherContenu() {
        System.out.println("colonne indexée");
        for (ValeurIndexee valIndexee : valeursIndexees) {
            System.out.println(valIndexee.valeur + ":" + valIndexee.index);
        }
        System.out.println("colonne");
        super.afficherContenu();
    }
    
    private void insererValeurIndexee(ValeurIndexee valIndexee) {
        int i = 0;
        while (i < valeursIndexees.size() && valeursIndexees.get(i).valeur.compareTo(valIndexee.valeur) < 0) {
            i++;
        }
        valeursIndexees.add(i, valIndexee);
    }
    
    private int rechercheDichotomique(V valeur) {
        int gauche = 0;
        int droite = valeursIndexees.size() - 1;

        while (gauche <= droite) {
            int milieu = gauche + (droite - gauche) / 2;
            int comparaison = valeursIndexees.get(milieu).valeur.compareTo(valeur);

            if (comparaison == 0) {
                return milieu;
            }

            if (comparaison < 0) {
                gauche = milieu + 1;
            } else {
                droite = milieu - 1;
            }
        }
        return -1;
    }

    private class ValeurIndexee {
        V valeur;
        int index;

        ValeurIndexee(V valeur, int index) {
            this.valeur = valeur;
            this.index = index;
        }
    }

    /** @Titre : Profilage des performances */
    public static void testPerformance(){
        final int nbEssaisMax = 10000;
        final int nbElementsMax = 10000;
        final int nbRecherchesparEssai = 5;

        // Stockage des temps d'exécution de recherche d'élément dans les colonnes
        long[] tempsExeColonneSimple = new long[nbEssaisMax];         // Utilisée plus tard
        long[] tempsExeColonneIndexee = new long[nbEssaisMax];  // pour comparaison de données

        Random aleatoire = new Random();
        for(int i = 0; i < nbEssaisMax; i++){
            // Chaque essai instancie une colonne simple et une colonne indexée
            Colonne<Integer> colonneSimple = new ColonneIndexee<>();
            ColonneIndexee<Integer> colonneIndexee = new ColonneIndexee<>();

            for(int j = 0; j < nbElementsMax; j++){
                int valeur = aleatoire.nextInt();
                // Chaque essai initialise les colonnes
                colonneSimple.ajouterValeur(valeur);
                colonneIndexee.ajouterValeur(valeur);
            }

            int elementTemoin = colonneSimple.obtenirValeur(aleatoire.nextInt(nbElementsMax));

            // Démarrage du chrono
            long debutColonneSimple = System.currentTimeMillis();
            for(int j = 0; j < nbRecherchesparEssai; j++){
                colonneSimple.obtenirIndex(elementTemoin);
            }
            tempsExeColonneSimple[i] = System.currentTimeMillis() - debutColonneSimple;

            long debutColonneIndexee = System.currentTimeMillis();
            for(int j = 0; j < nbRecherchesparEssai; j++){
                colonneIndexee.obtenirIndex(elementTemoin);
            }
            tempsExeColonneIndexee[i] = System.currentTimeMillis() - debutColonneIndexee;
        }

        double moyenneColonneSimple  = calculerMoyenneTempsExe(tempsExeColonneSimple);
        double moyenneColonneIndexee = calculerMoyenneTempsExe(tempsExeColonneIndexee);

    }
    private static double calculerMoyenneTempsExe(long[] tempsExeColonne){
        long somme = 0;
        for(long valeur : tempsExeColonne){
            somme += valeur;
        }
        return (double) somme / tempsExeColonne.length;
    }
}
