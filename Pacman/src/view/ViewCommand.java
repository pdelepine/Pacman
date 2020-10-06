package view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controleur.InterfaceControleur;
import model.Game;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewCommand implements Observateur{
	
	private JFrame fenetre;
	private int turn;
	private InterfaceControleur controleurGame;
	private JButton restart_button;
	private JButton run_button;
	private JButton step_button;
	private JButton pause_button;
	private JLabel nombreTours;
	
	public ViewCommand(InterfaceControleur CG) {
		this.controleurGame = CG;
		turn = 0;
		fenetre = new JFrame();
		fenetre.setTitle("Commandes");
		fenetre.setSize(500, 300);
		fenetre.setLocation(1350,400);
		fenetre.setResizable(false);
    	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	createView();
    	fenetre.setVisible(true);
    }
	
	public void set_turn(int turn) {
		this.turn = turn;
		nombreTours.setText("Turn: "+Integer.toString(turn));
	}

	public void createView(){
		    // Panel général constitué de 2 sous-panel top et bottom
			JPanel general = new JPanel();
			general.setLayout(new GridLayout(2,1));
			
			// Top Panel constitué des 4 buttons
			JPanel top_panel = new JPanel();
			top_panel.setLayout(new GridLayout(1,4));
			
			Icon restart_icon =  new ImageIcon("Resources/icons/icon_restart.png");
			restart_button = new JButton(restart_icon);
			top_panel.add(restart_button);
			
			Icon run_icon =  new ImageIcon("Resources/icons/icon_run.png");
			run_button = new JButton(run_icon);
			top_panel.add(run_button);
			
			Icon step_icon =  new ImageIcon("Resources/icons/icon_step.png");
			step_button = new JButton(step_icon);
			top_panel.add(step_button);
			
			Icon pause_icon =  new ImageIcon("Resources/icons/icon_pause.png");
			pause_button = new JButton(pause_icon);
			top_panel.add(pause_button);
			
			/* On ajoute des listeners pour que quand on clique sur un bouton
			* cela envoie un message au controleur
			*/
			restart_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controleurGame.start();					
				}
			});
			run_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controleurGame.run();			
				}
			});
			
			step_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controleurGame.step();				
				}
			});
			pause_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controleurGame.pause();		
				}
			});
			// Tous les boutons sauf restart sont désactivés au début de partie
			run_button.setEnabled(false);
			step_button.setEnabled(false);
			pause_button.setEnabled(false);
			
			
			//Bottom Panel constitué du slider et affichage du nombre de tours 
			JPanel bottom_panel = new JPanel();
			bottom_panel.setLayout(new GridLayout(1,2));
			
			// Slider
			JPanel sliderpanel = new JPanel();
			sliderpanel.setLayout(new GridLayout(2,0));
			
			JLabel sliderLabel = new JLabel("Number of turns per second", JLabel.CENTER);
	        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        
			JSlider speed = new JSlider(JSlider.HORIZONTAL,1,10,1);
			speed.setMajorTickSpacing(1);
			speed.setMinorTickSpacing(1);
			speed.setPaintTicks(true);
			speed.setPaintLabels(true);
			speed.setLabelTable(speed.createStandardLabels(1));
			
			speed.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					controleurGame.setTime(Integer.toUnsignedLong(speed.getValue()));					
				}
			});
			
			sliderpanel.add(sliderLabel);
			sliderpanel.add(speed);
			bottom_panel.add(sliderpanel);
			
			// Label des tours 
			nombreTours = new JLabel("Turn: "+ Integer.toString(turn),JLabel.CENTER);
			bottom_panel.add(nombreTours);
			
			// Ajout des 2 sous-panel au panel général
			general.add(top_panel);
			general.add(bottom_panel);
			
			fenetre.setContentPane(general);
		}
	/*
	 * On disable le bouton restart et enable step,run et pause
	 */
	public void changeStateButton() {
		restart_button.setEnabled(false);
		run_button.setEnabled(true);
		step_button.setEnabled(true);
		pause_button.setEnabled(true);
	}

	@Override
	public void update(Game game) {
		this.turn = game.getTurn();	
		nombreTours.setText("Current turn: "+Integer.toString(turn)); // Change le label sur l'inteface
		
		
	}
}
