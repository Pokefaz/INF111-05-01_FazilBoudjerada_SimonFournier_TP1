package vue.connexion;

import modele.Banque;
import vue.GestionnaireVue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CadreConnexion extends JFrame implements ActionListener {
    private JLabel nomUtilisateurText;
    private JTextField nomUtilisateurEdit;
    private JLabel motDePasseText;
    private JPasswordField motDePasseEdit;
    private JButton requeteAcceptee;
    private JLabel identifiantRejete = null;

    private Banque banque;
    private GestionnaireVue gestionnaireVue;

    public CadreConnexion(GestionnaireVue gestionnaireVue) {
        super("Connexion");
        initialiserComposants(gestionnaireVue);
        configurerFenetrePrincipale();
    }

    private void configurerFenetrePrincipale(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.DARK_GRAY);
        setSize(900, 600);
        setLocationRelativeTo(null);        // Pour que la fenêtre soit au centre
    }

    private void initialiserComposants(GestionnaireVue gestionnaireVue){
        this.gestionnaireVue = gestionnaireVue;

        JPanel panneauPrincipal = new JPanel();
        panneauPrincipal.setLayout(new GridLayout(4,2,5,5));
        // panneauPrincipal.setBackground(Color.DARK_GRAY);

        nomUtilisateurText = new JLabel("Nom d'utilisateur");
        nomUtilisateurText.setForeground(Color.WHITE);
        nomUtilisateurEdit = new JTextField();

        motDePasseText = new JLabel("Mot de passe");
        motDePasseText.setForeground(Color.WHITE);
        motDePasseEdit = new JPasswordField();

        requeteAcceptee = new JButton("Soumettre");
        requeteAcceptee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // effectuer les saisies de texte
                String nomUtilisateur = nomUtilisateurEdit.getText();
                String motDePasse = new String(motDePasseEdit.getPassword());

                // vérifier la paire nom d'utilisateur et mot de passe (Banque)
                if(banque.verifier(nomUtilisateur, motDePasse)) {
                    // Si valide
                    // définir l'utilisateur actif (Banque)
                    banque.setUtilisateurActif(nomUtilisateur);

                    // activer le mode compte (Gestionnaire Vue)
                    gestionnaireVue.activerModeCompte();

                    // réinitialiser les entrées du panneau de connexion
                    nomUtilisateurEdit.setText("");
                    motDePasseEdit.setText("");
                    identifiantRejete.setVisible(false);
                } else{

                    // Si invalide
                    // afficher le message de refus ("Accès refusé")
                    identifiantRejete.setVisible(true);
                }
            }
        });

        identifiantRejete = new JLabel("Accès refusé");
        identifiantRejete.setForeground(Color.WHITE);
        identifiantRejete.setVisible(false);


        panneauPrincipal.add(nomUtilisateurEdit);
        panneauPrincipal.add(nomUtilisateurText);
        panneauPrincipal.add(motDePasseEdit);
        panneauPrincipal.add(motDePasseText);
        panneauPrincipal.add(new JLabel());
        panneauPrincipal.add(requeteAcceptee);
        panneauPrincipal.add(new JLabel());
        panneauPrincipal.add(identifiantRejete);
        add(panneauPrincipal);

        setVisible(true);
    }
}
