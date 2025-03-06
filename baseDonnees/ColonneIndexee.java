package baseDonnees;

import baseDonnees.bases.InterfaceColonne;

import java.util.ArrayList;
import java.util.List;

public class ColonneIndexee<V> extends Colonne<V>{
    private List<V> valeurs;

    public ColonneIndexee(){
        this.valeurs = new ArrayList<V>();
    }

    @Override
    public void ajouterValeur(V valeur){
        valeurs.add(valeur);
    }
    @Override
    public V obtenirValeur(int index){
        return valeurs.get(index);
    }

    public boolean estUnique(V valeur){
        return valeurs.contains(valeur);
    }

    @Override
    public void changerValeur(int index, V valeur){
        this.valeurs.set(index, valeur);
    }
    public void afficherContenu(){
        System.out.println(valeurs);
    }
}
