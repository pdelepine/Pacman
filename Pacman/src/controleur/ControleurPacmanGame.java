package controleur;

import model.Maze;
import model.PacmanGame;
import view.ViewCommand;
import view.ViewPacmanGame;

public class ControleurPacmanGame implements InterfaceControleur{
	private PacmanGame game;
	private ViewPacmanGame view_game;
	private ViewCommand viewCommand;
	
	public ControleurPacmanGame(PacmanGame game) {
		this.game = game;
		this.game.enrengistrerObservateur(this);
		this.view_game 	 = new ViewPacmanGame();
		this.viewCommand = new ViewCommand(this);
	}	
	
	@Override
	public void start() {
		System.out.println("Bouton restart appuyé");
		game.init();
		viewCommand.changeStateButton();
		game.launch();
		
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

	@Override
	public void actualize() {
		viewCommand.set_turn(game.getTurn());
		view_game.setTurn(game.getTurn());	
	}

}
