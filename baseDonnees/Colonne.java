package baseDonnees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colonne<V>{
    private V[] valeurs;
    private int nbElements;

    public Colonne(){
        this(10);
    }
    public Colonne(int tailleInitiale){
        valeurs = (V[]) new Object[tailleInitiale];
        nbElements = 0;
    }

    public void ajouterValeur(V valeur){
        if(nbElements == valeurs.length){
            valeurs = Arrays.copyOf(valeurs, valeurs.length * 2);
        }
        valeurs[nbElements++] = valeur;
    }
    public V obtenirValeur(int index){
        verifierIndex(index);
        return valeurs[index];
    }
    public int obtenirIndex(V valeur){
        for(int i = 0; i < nbElements; i++){
            if(valeurs[i].equals(valeur)){
                return i;
            }
        }
        return -1;
    }
    public void changerValeur(int index, V valeur){
        verifierIndex(index);
        valeurs[index] = valeur;
    }
    public int getNbElements(){
        return nbElements;
    }
    public void afficherContenu(){
        System.out.println(Arrays.toString(valeurs));
    }
    private void verifierIndex(int index){
        if(index < 0 || index >= nbElements){
            throw new IndexOutOfBoundsException("Pas de valeur à l'index " + index);
        }
    }
}
