package controleur;

import model.PacmanGame;
import view.PanelPacmanGame;
import view.ViewCommand;
import view.ViewPacmanGame;

public class ControleurPacmanGame implements InterfaceControleur{
	private PacmanGame game;
	private ViewPacmanGame view_game;
	private ViewCommand view_command;
	
	public ControleurPacmanGame(PacmanGame game) {
		this.game = game;
		this.view_game 	 = new ViewPacmanGame(game);
		this.view_command = new ViewCommand(this);
		this.game.enrengistrerObservateur(this.view_game);
		this.game.enrengistrerObservateur(this.view_command);
		
	}	
	
	@Override
	public void start() {
		System.out.println("Bouton restart appuyé");
		game.init(); // on initialise le jeu
		view_command.changeStateButton(); //on change les status des boutons 
		//game_panel = new PanelPacmanGame(game.getMaze()); // on crée le panelPacman à partir du maze de game
		view_game.setGame_panel(new PanelPacmanGame(game.getMaze()));// on envoie le panelPacman à la view
		game.launch();// on lance le jeu
	}

	@Override
	public void step() {
		System.out.println("Bouton step appuyé");
		game.step();		
	}

	@Override
	public void run() {
		System.out.println("Bouton run appuyé");
		game.launch();		
	}

	@Override
	public void pause() {
		game.pause();		
	}

	@Override
	public void setTime(double time) {
		int t = (int)time;
		System.out.println("------ Vitesse modifié : "+ Integer.toString(t)+" tours par second ------");
		if(time !=0) {
			game.setTime((long)(1000/time));
		}		
	}
	
	public void actionInteractive(String s, int numeroJoueur) {
		game.actionInteractive(s,numeroJoueur);
	}

	public boolean isModeInteractif() {
		return game.isModeInteractif();
	}
}
