package model;

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

public class Test{
	public static ControleurPacmanGame cpg;
	
	public static void main(String[] args) {
		
	 
		//SimpleGame simplegame = new SimpleGame(100,4000);
		//ControleurSimpleGame CG = new ControleurSimpleGame(simplegame);
		
		PacmanGame pacman = new PacmanGame(1000, 3000);
		//ControleurPacmanGame cpg; 
		
		//Création d'un file chooser pour lister les layouts
		JFileChooser jfc = new JFileChooser();
		//On se déplace dans le dossier ./Resources/layouts
		jfc.setCurrentDirectory(new File("./Resources/layouts"));
		//On récupère la liste des fichiers dans un string
		String[] layout_names = jfc.getCurrentDirectory().list();
		Arrays.sort(layout_names);
		
		// Création de la fenêtre de sélection de layout
		JFrame layout_selector_frame = new JFrame();
		layout_selector_frame.setTitle("Sélectionnez un labyrinthe");
		layout_selector_frame.setSize(600, 100);
		layout_selector_frame.setLocationRelativeTo(null);
		layout_selector_frame.setResizable(false);
		layout_selector_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Création du menu déroulant
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
		//Lorsque l'on sélectionne un layout on l'envoie a PacmanGame
		modeDeJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.out.println(modeDeJeu.getSelectedItem());
					if(modeDeJeu.getSelectedItem().equals("Ordi vs Ordi")) {
						pacman.setNbPacmanInteractif(0);
						pacman.setNbFantomeInteractif(0);
					}else if(modeDeJeu.getSelectedItem().equals("Joueur(Pacman) vs Ordi")) {
						pacman.setNbPacmanInteractif(1);
						pacman.setPacmanInteractif(true);
						pacman.setModeInteractif(true);
						
					}else if(modeDeJeu.getSelectedItem().equals("Joueur(Fantôme) vs Ordi")) {
						pacman.setNbFantomeInteractif(1);
						pacman.setFantomeInteractif(true);
						pacman.setModeInteractif(true);
						
					}else if(modeDeJeu.getSelectedItem().equals("Joueur(Pacman) vs Joueur(Pacman)")) {
						pacman.setNbPacmanInteractif(2);
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
		JButton button_choose = new JButton("Start");
		button_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(buttonModeInteractif.isSelected()) {
					pacman.setModeInteractif(true);
				}*/
				cpg = new ControleurPacmanGame(pacman);
				cpg.start();
				layout_selector_frame.dispose(); // On ferme la fenêtre devenu inutile
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
		panel.add(button_choose);
		
		//panel2.add(buttonModeInteractif);
		panel2.add(modeDeJeu);
		
		general.add(panel);
		general.add(panel2);
		
		layout_selector_frame.add(general);
		//On affiche la fenêtre de sélection
		layout_selector_frame.setVisible(true);	
	}
}
