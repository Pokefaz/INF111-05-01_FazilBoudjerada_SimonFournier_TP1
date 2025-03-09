package baseDonnees;

import baseDonnees.bases.InterfaceColonne;

import java.util.*;

public class ColonneIndexee<V> extends Colonne<V>{
    private List<V> valeurs;
    private Map<V,Integer> indexMap;

    public ColonneIndexee(){
        this.valeurs = new ArrayList<V>();
        this.indexMap = new HashMap<>();
    }


    @Override
    public void ajouterValeur(V valeur){
        if(!indexMap.containsKey(valeur)){
            valeurs.add(valeur);
            indexMap.put(valeur, valeurs.size() - 1);
        }
    }

    @Override
    public int obtenirIndex(V valeur){
        return indexMap.getOrDefault(valeur, -1);
    }

    public boolean estUnique(V valeur){
        return !indexMap.containsKey(valeur);
    }

    @Override
    public void changerValeur(int index, V valeur){
        // Pas supporté par ColonneIndexee
        throw new UnsupportedOperationException("\nImpossible de faire un changement de valeur" +
                " d'object de type ColonneIndexee.\n");
    }

    @Override
    public void afficherContenu(){
        for(Map.Entry<V,Integer> entry : indexMap.entrySet()){
            System.out.println("Index : " + entry.getValue() + " - Valeur : " + entry.getKey());
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
