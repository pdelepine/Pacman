package Model;

import Controleur.ControleurSimpleGame;

public abstract class Game implements Runnable , MyObservable{
	protected int turn;// compteur du nombre de tours du jeu
	protected final int maxturn;
	private boolean isRunning;// permet de savoir si le jeu est en pause ou non
	private Thread thread;
	private long time;
	private ControleurSimpleGame _controleurGame;
  
	// Initialise le jeu en :
	// - remettant le compteur de tour turn à zéro
	// - met isRunning à true
	// - appelle initializeGame
	public Game(int maxturn,long time) {
		this.time = time;
		this.maxturn = maxturn;
	}
	
	
	public void init() {
		this.turn = 0;
		this.isRunning = true;
		initializeGame();
	}
	
	public void step() {
		//Incrémente le compteur de tour du jeu
		// appelle taketurn() si le jeu n'est pas terminé
		if(gameContinue()) {
			this.turn++;
			takeTurn();
			notifierObservateur();
		}else {
			this.isRunning = false;
			gameOver();
		}
	}
	
	//Lance le jeu en appelant la méthode step jusqu'à la fin
	public void run() {
		System.out.println("Test");
		while(isRunning) {
			System.out.println("entre dans boucle");
			step();
			try {
			Thread.sleep(time);
			}catch (Exception exp) {
				System.out.println(exp.getMessage());
			}
		}
  }
	public void launch() {
		this.isRunning=true;
		try {
		thread = new Thread(this);
		thread.start();
		}catch (Exception exp) {
			System.out.println(exp.getMessage());
			
		}
  }
	public void pause() {
		if(isRunning) {
			this.isRunning = false;
		}else {
			this.isRunning = true;
		}
		

	}
	
	public abstract void initializeGame();
	public abstract void takeTurn();
	public abstract boolean gameContinue();
	public abstract void gameOver();
	
	public void enrengistrerObservateur(ControleurSimpleGame CG) {
		this._controleurGame = CG;
	}
	public void supprimerObservateur() {
		
	}
	public void notifierObservateur() {
		_controleurGame.actualizeTurn();
	}


	public int getTurn() {
		return turn;
	}


	public void setTurn(int turn) {
		this.turn = turn;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}


	public void setTime(long time) {
		this.time = time;
	}
}
