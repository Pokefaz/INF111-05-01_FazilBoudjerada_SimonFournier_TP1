package baseDonnees.modeles;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Utilisateur {
    private String nomUtilisateur;
    private String numeroDeCompte;
    private double solde;
    private byte[] salt;
    private byte[] hashPassword;

    public Utilisateur(String nomUtilisateur, String motDePassee,
                       String numeroDeCompte, double solde){
        this.nomUtilisateur = nomUtilisateur;
        this.hashPassword = hacherMotDePasse(motDePassee, this.getSalt());
        this.numeroDeCompte = numeroDeCompte;
        this.solde = solde;
    }
    public Utilisateur(String nomUtilisateur, byte[] hashMotDePasse,
                       byte[] salt, String numeroDeCompte, double solde){
        this.nomUtilisateur = nomUtilisateur;
        this.hashPassword = hashMotDePasse;
        this.salt = salt;
        this.numeroDeCompte = numeroDeCompte;
        this.solde = solde;
    }

    public String getNomUtilisateur(){
        return nomUtilisateur;
    }
    public String getNumeroDeCompte(){
        return numeroDeCompte;
    }
    public double getSolde(){
        return solde;
    }
    public byte[] getSalt(){
        return salt;
    }
    public byte[] getHashPassword() {
        return hashPassword;
    }

    public void setNomUtilisateur(String nom){
        this.nomUtilisateur = nom;
    }
    public void setNumeroDeCompte(String numCompte){
        this.numeroDeCompte = numCompte;
    }
    public void setSolde(double solde1){
        this.solde = solde1;
    }
    public void setSalt(byte[] salt1){
        this.salt = salt1;
    }
    public void setHashPassword(byte[] hashPassword1){
        this.hashPassword = hashPassword1;
    }

    private byte[] hacherMotDePasse(String motDePasse, byte[] salt){
        try {
            MessageDigest md4 = MessageDigest.getInstance("SHA-256");
            md4.update(salt);
            return md4.digest(motDePasse.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean authentifier(String nomUtilisateur, String motDePasse){
        if(!this.nomUtilisateur.equals(nomUtilisateur)){
            return false;
        }
        byte[] hacherTentative = hacherMotDePasse(motDePasse, this.salt);
        return Arrays.equals(this.hashPassword, hacherTentative);
    }
    public void transactionSurSolde(double differentiel){
        this.solde += differentiel;
    }
    @Override
    public String toString() {
        return "Utilisateur {" +
                "nomUtilisateur: " + nomUtilisateur + "," +
                "numeroDeCompte: " + numeroDeCompte + "," +
                "solde: " + solde + "," +
                "salt: " + Arrays.toString(salt) + "," +
                "hashMotDePasse: " + Arrays.toString(hashPassword) + ","
                + '}';
    }
}