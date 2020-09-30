package Controleur;

import Model.Game;
import View.ViewCommand;
import View.ViewSimpleGame;

public class ControleurSimpleGame implements InterfaceControleur{
	private Game _game;
	private ViewCommand _viewCommand;
	private ViewSimpleGame _viewSimpleGame;
	public ControleurSimpleGame(Game game){
		this._game = game;
		_game.enrengistrerObservateur(this);
		this._viewCommand = new ViewCommand(this);
		this._viewSimpleGame = new ViewSimpleGame(this);
	}

	@Override
	public void start() {
		System.out.println("Bouton restart appuyé");
		_game.init();
		_viewCommand.changeStateButton();
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
		_game.run();
		
	}

	@Override
	public void pause() {
		System.out.println("Bouton pause appuyé");
		_game.pause();
		
	}

	@Override
	public void setTime(double time) {
		int t = (int)time;
		System.out.println("Silder modifié : "+ Integer.toString(t));
		if(time !=0) {
			_game.setTime((long)(1000/time));
		}
	}
	
	public void actualizeTurn() {
		_viewCommand.set_turn(_game.getTurn());
		_viewSimpleGame.setTurn(_game.getTurn());
	}
}
