

public class SimpleGame extends Game {
	
	public SimpleGame(int maxturn, long time) {
		super(maxturn, time);
		
	}
	public void initializeGame() {
		
	}
	public void takeTurn(){
		System.out.println("Tour "+Integer.toString(turn)+" du jeu en cours");
	}
	public boolean gameContinue(){
		
		return this.turn<=this.maxturn;
	}
	public void gameOver(){
		
	}
	
	
	
	

}
