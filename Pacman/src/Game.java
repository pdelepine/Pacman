
public abstract class Game {
	private int turn;// compteur du nombre de tours du jeu
	private final int maxturn;// nombre de tours maximum
	private boolean isRunning;// permet de savoir si le jeu est en pause ou non
	
	// Initialise le jeu en :
	// - remettant le compteur de tour turn à zéro
	// - met isRunning à true
	// - appelle initializeGame
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
		}else {
			this.isRunning = false;
			gameOver();
		}
	}
	
	//Lance le jeu en appelant la méthode step jusqu'à la fin
	public void run() {
		
	}
	
	public abstract void initializeGame();
	public abstract void takeTurn();
	public abstract boolean gameContinue();
	public abstract void gameOver();
}