package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.ControleurPacmanGame;
import model.PacmanGame;

public class ViewMenu {
	private JFrame menu_demarrer;
	private ControleurPacmanGame controleur;
	private PacmanGame pacman;
	
	public ViewMenu(PacmanGame pacman) {
		this.pacman = pacman;
		menu_demarrer = new JFrame();
		menu_demarrer.setTitle("Sélectionnez un labyrinthe");
		menu_demarrer.setSize(600, 100);
		menu_demarrer.setLocationRelativeTo(null);
		menu_demarrer.setResizable(false);
		menu_demarrer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createView();
	}
	
	private void createView() {
		//Création d'un file chooser pour lister les layouts
		JFileChooser jfc = new JFileChooser();
		//On se déplace dans le dossier ./Resources/layouts
		jfc.setCurrentDirectory(new File("./Resources/layouts"));
		//On récupère la liste des fichiers dans un string
		String[] layout_names = jfc.getCurrentDirectory().list();
		// Tri alphabétique
		Arrays.sort(layout_names);
		
		//Création du menu déroulant pour choisir le layout du niveau
		JComboBox<String> layout_list = new JComboBox<String>(layout_names);
		layout_list.addItem("Choix de layout"); // On ajoute un choix vide 
		layout_list.setSelectedIndex(layout_list.getItemCount()-1); // On met que le choix sélectionné soit la chaine vide
		//Lorsque l'on sélectionne un layout on l'envoie a PacmanGame
		layout_list.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				String layout_chosen = (String)cb.getSelectedItem();
				pacman.setLayout_chosen(jfc.getCurrentDirectory()+"/"+layout_chosen);

			}
		});	
		
		//Création du menu déroulant choix de l'agent à contrôler
		JComboBox<String> modeDeJeu = new JComboBox<String>();
		modeDeJeu.addItem("Mode de Jeu"); // On ajoute un choix vide 
		modeDeJeu.addItem("Ordi vs Ordi"); 
		modeDeJeu.addItem("Joueur(Pacman) vs Ordi"); 
		modeDeJeu.addItem("Joueur(Fantôme) vs Ordi"); 
		modeDeJeu.addItem("Joueur(Pacman) vs Joueur(Pacman)"); 
		modeDeJeu.addItem("Joueur(Pacman) vs Joueur(Fantôme)"); 
		modeDeJeu.setSelectedIndex(0); // On met que le choix sélectionné soit la chaine vide
		
		modeDeJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.out.println(modeDeJeu.getSelectedItem());
					if(modeDeJeu.getSelectedItem().equals("Ordi vs Ordi")) {
						pacman.setNbPacmanInteractif(0);
						pacman.setNbFantomeInteractif(0);
					}else if(modeDeJeu.getSelectedItem().equals("Joueur(Pacman) vs Ordi")) {
						pacman.setNbPacmanInteractif(1);
						pacman.setNbFantomeInteractif(0);
						pacman.setPacmanInteractif(true);
						pacman.setModeInteractif(true);
						
					}else if(modeDeJeu.getSelectedItem().equals("Joueur(Fantôme) vs Ordi")) {
						pacman.setNbPacmanInteractif(0);
						pacman.setNbFantomeInteractif(1);
						pacman.setFantomeInteractif(true);
						pacman.setModeInteractif(true);
						
					}else if(modeDeJeu.getSelectedItem().equals("Joueur(Pacman) vs Joueur(Pacman)")) {
						pacman.setNbPacmanInteractif(2);
						pacman.setNbFantomeInteractif(0);
						pacman.setPacmanInteractif(true);
						pacman.setModeInteractif(true);
						
					}else if(modeDeJeu.getSelectedItem().equals("Joueur(Pacman) vs Joueur(Fantôme)")) {
						pacman.setNbPacmanInteractif(1);
						pacman.setNbFantomeInteractif(1);
						pacman.setPacmanInteractif(true);
						pacman.setFantomeInteractif(true);
						pacman.setModeInteractif(true);
					}
				}
			});
		
		//Création du bouton pour lancer le jeu après avoir choisis un layout
		JButton button_start = new JButton("Start");
		button_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur = new ControleurPacmanGame(pacman); // initialisation du controleur
				controleur.start();// On lance le jeu
				menu_demarrer.dispose(); // On ferme la fenêtre devenu inutile
			}
		});
		
		// Création du panel général;
		JPanel general = new JPanel();
		general.setLayout(new BoxLayout(general, BoxLayout.Y_AXIS));
		
		// Création d'un panel avec un layout horizontal
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		// Création d'un panel avec un layout horizontal
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

		//On assemble tout
		panel.add(layout_list);
		panel.add(button_start);

		panel2.add(modeDeJeu);
		
		general.add(panel);
		general.add(panel2);
		
		menu_demarrer.add(general);

	}
	
	public void launch() {
		menu_demarrer.setVisible(true);
	}

}
