package model;

public class SimpleGame extends Game {
	
	public SimpleGame(int maxturn, long time) {
		super(maxturn, time);
		
	}
	public void initializeGame() {
		System.out.println("Jeu initialisé");
		
	}
	public void takeTurn(){
		System.out.println("\t Tour "+Integer.toString(turn)+" du jeu en cours");
	}
	public boolean gameContinue(){
		System.out.print("\t Test si jeu terminé");
		System.out.println(" " + Boolean.toString(this.turn<=this.maxturn));
		return this.turn<this.maxturn;
	}
	public void gameOver(){
		System.out.println("Le jeu est terminé");
	}
}
