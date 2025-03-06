package baseDonnees;

import baseDonnees.bases.InterfaceColonne;

import java.util.ArrayList;
import java.util.List;

public class Colonne<V> {
    private List<V> valeurs;

    public Colonne(){
        this.valeurs = new ArrayList<>();
    }

    public void ajouterValeur(V valeur){
        valeurs.add(valeur);
    }
    public V obtenirValeur(int index){
        verifierIndex(index);
        return valeurs.get(index);
    }
    public int obtenirIndex(V valeur){
        return valeurs.indexOf(valeur);
    }
    public void changerValeur(int index, V valeur){
        verifierIndex(index);
    }
    public int getNbElements(){
        return valeurs.size();
    }
    public void afficherContenu(){
        System.out.println(valeurs);
    }
    private void verifierIndex(int index){
        
    }
}
