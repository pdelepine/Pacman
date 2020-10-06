package controleur;

import model.Game;
import view.ViewCommand;
import view.ViewSimpleGame;

public class ControleurSimpleGame implements InterfaceControleur{
	private Game _game;
	private ViewCommand _viewCommand;
	private ViewSimpleGame _viewSimpleGame;
	public ControleurSimpleGame(Game game){
		this._game = game;
		this._viewCommand = new ViewCommand(this);
		this._viewSimpleGame = new ViewSimpleGame();
		this._game.enrengistrerObservateur(_viewCommand);
		this._game.enrengistrerObservateur(_viewSimpleGame);
		
	}

	@Override
	public void start() {
		System.out.println("Bouton restart appuyé");
		_game.init();
		//_viewCommand.changeStateButton();// chnage l'état des boutons
		_game.launch();		
	}

	@Override
	public void step() {
		System.out.println("Bouton step appuyé");
		_game.step();		
	}

	@Override
	public void run() {
		System.out.println("Bouton run appuyé");
		_game.launch();		
	}

	@Override
	public void pause() {
		_game.pause();		
	}

	@Override
	// change le nombre de tours par seconde
	public void setTime(double time) {
		int t = (int)time;
		System.out.println("------ Vitesse modifié : "+ Integer.toString(t)+" tours par second ------");
		if(time !=0) {
			_game.setTime((long)(1000/time));
		}
	}
	
}
