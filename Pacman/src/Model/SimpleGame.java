package Model;


public class SimpleGame extends Game {
	
	public SimpleGame(int maxturn, long time) {
		super(maxturn, time);
		
	}
	public void initializeGame() {
		System.out.println("Jeu initialisé");
		
	}
	public void takeTurn(){
		System.out.println("Tour "+Integer.toString(turn)+" du jeu en cours");
	}
	public boolean gameContinue(){
		System.out.println("Test si jeu terminé");
		return this.turn<=this.maxturn;
	}
	public void gameOver(){
		System.out.println("Le jeu est terminé");
	}
	
	
	
	

}
