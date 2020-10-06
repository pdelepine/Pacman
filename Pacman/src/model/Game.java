package model;

import java.util.ArrayList;

import controleur.InterfaceControleur;

public abstract class Game implements Runnable , MyObservable{
	protected int turn;// compteur du nombre de tours du jeu
	protected final int maxturn;
	private boolean isRunning;// permet de savoir si le jeu est en pause ou non
	private Thread thread;
	private long time;
	private ArrayList<InterfaceControleur> _controleurs;
  
	// Initialise le jeu en :
	// - remettant le compteur de tour turn à zéro
	// - met isRunning à true
	// - appelle initializeGame
	public Game(int maxturn,long time) {
		this.time = time;
		this.maxturn = maxturn;
		_controleurs = new ArrayList<InterfaceControleur>();
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
	public void run(){
		while(isRunning) {
			System.out.println("Le jeu continue !");
			step();
			try {
				Thread.sleep(time);
			}catch (InterruptedException exp) {
				System.out.println("Thread interrupted !");
				System.out.println(exp.getMessage());
			}
		}
		if(!isRunning) {
			System.out.println("------ Thread tué -------");
		}
  }
	public void launch() {
		this.isRunning=true;
		try {
		this.thread = new Thread(this);
		this.thread.start();
		}catch (Exception exp) {
			System.out.println(exp.getMessage());
			
		}
  }
	public void pause() {
		if(isRunning) {
			System.out.println("------ Jeu en pause -------");
			this.isRunning = false;
		}
		
	}
	
	public abstract void initializeGame();
	public abstract void takeTurn();
	public abstract boolean gameContinue();
	public abstract void gameOver();
	
	public void enrengistrerObservateur(InterfaceControleur CG) {
		this._controleurs.add(CG);
	}
	public void supprimerObservateur(InterfaceControleur CG) {
		this._controleurs.remove(CG);
		
	}
	public void notifierObservateur() {
		for(InterfaceControleur i : _controleurs) {
			i.actualize();
		}
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
